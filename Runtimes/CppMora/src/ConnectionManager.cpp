
#include "internal/precomp.h"
#include "internal/ConnectionManager.h"
#include "internal/MoraMessages.h"

#include "MORAExt.h"

#include <chrono>
#include <thread>

using namespace mora;


ConnectionStack::ConnectionStack(RemoteCommunicator* rc, Options* opt)
	: target(*rc), options(opt), connections(opt->maxConnectionsPerClient, NULL)
{
}
ConnectionStack::~ConnectionStack()
{
}

AbstractConnection* ConnectionStack::getConnection() {
	for (size_t i = 0; i < connections.size(); i++) {
		if (connections[i] == NULL) {
			AbstractConnection* con = ConnectionFactory::createConnection(&target, options);
			CHECK_NOTNULL_F(con, "Could not create a connection");
			connections[i] = con;
			con->used = true;
			return con;
		}
		else if (connections[i]->used == false) {
			connections[i]->used = true;
			return connections[i];
		}
	}
	return NULL;
}
void ConnectionStack::release(AbstractConnection* con) {
	con->used = false;
}
void ConnectionStack::close() {
	for (size_t i = 0; i < connections.size(); i++) {
		connections[i]->close();
		delete connections[i];
		connections[i] = NULL;
	}
	connections.clear();
}











ConnectionManager::ConnectionManager(Options* opt)
	: mOptions{ opt }, mConnectionStacks()
{
}
ConnectionManager::~ConnectionManager() {
	for (auto it = mConnectionStacks.begin(); it != mConnectionStacks.end(); ++it) {
		it->second->close();
		delete it->second;
	}
	mConnectionStacks.clear();
}

bool ConnectionManager::send(const OutMessage& msg) {
	ConnectionStack* stack = NULL;

	const std::string key = msg.target->toString();

	auto stackIt = mConnectionStacks.find(key);
	if (stackIt == mConnectionStacks.end()) {
		stack = new ConnectionStack(msg.target, mOptions);
		mConnectionStacks.insert(std::make_pair(key, stack));
	}
	else
		stack = stackIt->second;

	CHECK_NOTNULL_F(stack, "Failed to gather connection stack for: %s", msg.target->toString().c_str());
	AbstractConnection* con = stack->getConnection();
	//try a few times to get a connection
	int attempts = 0;
	while (con == NULL && attempts < mOptions->maxConnectionsPerClient) {
		std::this_thread::sleep_for(std::chrono::milliseconds(5));
		con = stack->getConnection();
	}
	if (con == NULL) {
		LOG_F(WARNING, "Failed to get connection for target: %s, after %i attempts", msg.target->toString().c_str(), mOptions->maxConnectionsPerClient);
		return false;
	}

	auto _ = finally([stack, con] {stack->release(con);  });
	try {
		std::vector<net::OutMsg> messages = net::OutMsg::create(msg.stream, con->getMaximumMessageSize());
		bool result = true;
		for (size_t i = 0; i < messages.size(); ++i) {
			if (con->send(messages[i]) == false) {
				result = false;
				break;
			}
		}
		return true;
	}
	catch (...) {
		LOG_F(ERROR, "Failed to send message with unknown error");
		return false;
	}

}