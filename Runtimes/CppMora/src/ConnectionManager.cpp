/*
 * ConnectionManager.cpp
 *
 *  Created on: 24.02.2020
 *      Author: sschweigert
 */

#include "private/ConnectionManager.h"
#include "private/TCPConnections.h"
#include "private/UDPConnections.h"

#include "MoraRemoteMethodCall.h"
#include "MoraException.h"

#include <Poco/Format.h>
#include <Poco/Net/NetException.h>
#include <thread>


using namespace mora;


ConnectionStack::ConnectionStack(RemoteCommunicator rc, const Options& opt)
	: mTarget{ rc }, mOptions{ opt }, mConnections(opt.maxConnectionsPerClient, ConnectionStore{ nullptr, false })
{
}
ConnectionStack::~ConnectionStack()
{
}

AbstractConnection* ConnectionStack::getConnection() {
	//TODO: maybe we have to protect the access through mutex?
	for (size_t i = 0; i < mConnections.size(); i++) {
		if (mConnections[i].first == nullptr ) {
			AbstractConnection* con{ nullptr };
			try {
				switch (mTarget.protocol()) {
				case Protocol::TCP:
					con = new TCPConnection(mTarget, mOptions);
					break;
				case Protocol::UDP:
					con = new UDPConnection(mTarget, mOptions);
					break;
				}
			}
			catch (Poco::Exception pexp) {
				throw MoraException(pexp.displayText().c_str());
			}
			catch (std::exception exp) {
				throw MoraException(exp.what());
			}
			catch (...) {
				throw MoraException(Poco::format("Failed to create connection to: %s", mTarget.qualifiedIdentifier().c_str()).c_str());
			}

			mConnections[i] = ConnectionStore{ con, true };
			return con;
		}
		else if (mConnections[i].second == false) {
			mConnections[i].second = true;
			return mConnections[i].first;
		}
	}
	return NULL;
}
void ConnectionStack::release(AbstractConnection* con) {
	for (size_t i = 0; i < mConnections.size(); i++) {
		if (mConnections[i].first == con) {
			mConnections[i].second = false;
			break;
		}
	}
}
void ConnectionStack::close() {
	for (size_t i = 0; i < mConnections.size(); i++) {
		if (mConnections[i].first != nullptr) {
			mConnections[i].first->close();
			delete mConnections[i].first;
			mConnections[i].first = nullptr;
		}
	}
}










ConnectionManager::ConnectionManager(const Options& opt)
	: mOptions{ opt }, mConnectionStacks{}
{
}
ConnectionManager::~ConnectionManager() {
	for (auto it = mConnectionStacks.begin(); it != mConnectionStacks.end(); ++it) {
		it->second->close();
		delete it->second;
	}
	mConnectionStacks.clear();
}

bool ConnectionManager::send(const IRemoteMethodCall& msg) {
	

	const RemoteMethod& remoteMethod = msg.target();
	const RemoteObject& remoteObject = remoteMethod.remoteObject();
	const RemoteCommunicator& remoteCommunicator = remoteObject.remoteCommunicator();
	
	const std::string key = remoteCommunicator.qualifiedIdentifier();
	
	ConnectionStack* stack = NULL;
	auto stackIt = mConnectionStacks.find(key);
	if (stackIt == mConnectionStacks.end()) {
		stack = new ConnectionStack(remoteCommunicator, mOptions);
		mConnectionStacks.insert(std::make_pair(key, stack));
	}
	else
		stack = stackIt->second;

	AbstractConnection* con = stack->getConnection();
	//try a few times to get a connection
	int attempts = 0;
	while (con == NULL && attempts < mOptions.maxConnectionsPerClient) {
		std::this_thread::sleep_for(std::chrono::milliseconds(2));
		con = stack->getConnection();
	}
	if (con == NULL) {
		throw MoraTransportException(Poco::format("Failed to get connection for target: %s, after %i attempts", remoteCommunicator.qualifiedIdentifier().c_str(), mOptions.maxConnectionsPerClient).c_str());
	}

	auto _ = finally([stack, con] {stack->release(con);  });
	try {
		std::vector<net::OutMsg> messages = net::OutMsg::create(msg.outputStream(), con->getMaximumMessageSize());
		bool result = true;
		for (size_t i = 0; i < messages.size(); ++i) {
			if (con->send(messages[i]) == false) {
				result = false;
				break;
			}
		}
		return result;
	}
	catch (...) {
		throw MoraException("Failed to send message with unknown error");
	}
	return true;
}
