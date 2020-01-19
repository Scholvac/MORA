
#include "internal/precomp.h"
#include "internal/TCPConnections.h"

#include "ActiveSocket.h"
#include "PassiveSocket.h"

#include <loguru.hpp>

#include <thread>
#include <map>


#define MAX_ERROR_COUNT 3
#define INITIAL_BUFFER_SIZE 1024

//////////////////////////	TCPServer /////////////////////////////
namespace mora {
	class TCPClientWorker {
		using InMsgCollectionMap = std::map<mora::int8, net::InMsgCollection*>;
	public:
		CActiveSocket*			client;
		TCPServer*				server;
		IMessageHandler*		handler;
		std::thread*			thread;
		mora::int8				ack_buffer[4];

		InMsgCollectionMap		collections;

		const char* adr;
		int port;
		bool alive;

		TCPClientWorker(TCPServer* s, IMessageHandler* h, CActiveSocket* c)
			: client(c), server(s), thread(NULL), adr(0), port(0), alive(false), handler(h)
		{
			thread = new std::thread(&TCPClientWorker::handleClient, this);
		}
		~TCPClientWorker() {
			if (thread != NULL) {
				client->Close();
				if (alive)
					thread->join();
			}
			delete client;
		}

		void handleClient() {
			adr = client->GetClientAddr();
			port = client->GetClientPort();

			LOG_F(INFO, "Starting new Client %s:%i", adr, port);

			alive = true;
			int errors = 0;
			net::InMsg* msg = new net::InMsg(INITIAL_BUFFER_SIZE);

			while (alive) {
				int32 bytesRead = client->Receive(msg->getRemainingCapcity(), (uint8*)msg->getInsertBuffer());
				if (bytesRead == 0) {
					LOG_F(WARNING, "Client %s:%i closed the connection", adr, port);
					alive = false;
				}
				else if (bytesRead < 0) {
					LOG_F(WARNING, "An Error occured with client: %s:%i", adr, port);
					errors++;
					if (errors > MAX_ERROR_COUNT)
						alive = false;
				}
				else {
					if (msg->handleNextBytes(bytesRead)) {
						handleFinishedMessage(msg);
						msg = new net::InMsg(INITIAL_BUFFER_SIZE);
					}
				}
				//LOG_F(INFO, "Received: %i bytes", bytesRead);
			}

			LOG_F(INFO, "Closing Client %s:%i", adr, port);
			server->stopClient(this);
		}

		void handleFinishedMessage(net::InMsg* msg) {
			if (msg->requiresAcknowledge(&(ack_buffer[0]))) {
				client->Send((const uint8*)(&ack_buffer[0]), 4);
			}
			net::InMsgCollection* coll = NULL;
			std::map<mora::int8, net::InMsgCollection*>::iterator it = collections.end();
			if (msg->numberOfMessages == 1) {
				coll = new net::InMsgCollection(msg, client->GetClientAddr());
			}
			else {
				it = collections.find(msg->magic);
				if (it == collections.end()) {
					collections.insert(std::pair<mora::int8, net::InMsgCollection*>(msg->magic, coll = new net::InMsgCollection(msg, client->GetClientAddr())));
				}
				else {
					coll = it->second;
					coll->append(msg);
				}
			}
			CHECK_NOTNULL_F(coll);
			if (coll->isComplete()) {
				if (it != collections.end())
					collections.erase(it);
				handler->handleIncommingMessage(coll);
			}
		}

	};
}

using namespace mora;

TCPServer::TCPServer() {

}
TCPServer::~TCPServer() {
	if (mSocket)
		stopServer();
}

bool TCPServer::startServer(Communicator* com, IMessageHandler* handler) {
	CHECK_F(mAcceptorThread == NULL, "Server already started");
	mCommunicator = com;
	mMessageHandler = handler;
	const Options& opt = com->getOptions();
	mAcceptorThread = new std::thread(&TCPServer::listen, this, opt);
	return mAcceptorThread != NULL;
}

bool TCPServer::stopServer() {
	if (mSocket == NULL) {
		LOG_F(INFO, "Socket has not been started yet");
		return true;
	}
	mAlive = false;
	mSocket->Close();
	mAcceptorThread->join();

	for (auto it = mWorkerThreads.begin(); it != mWorkerThreads.end(); ++it) {
		mWorkerThreads.erase(it);
		TCPClientWorker* w = (*it);
		delete w;//deletes client and joins thread
	}

	return true;
}

Communicator* TCPServer::getCommunicator() {
	return mCommunicator;
}
RemoteCommunicator* TCPServer::getResponseAddress() {
	return mResponseAdr;
}

void TCPServer::listen(const Options& opt) {
	mSocket = new CPassiveSocket(CSimpleSocket::CSocketType::SocketTypeTcp);
	if (!mSocket->Initialize())
		LOG_F(ERROR, "Failed to initialize server socket");
	if (!mSocket->Listen(opt.host.c_str(), opt.port, opt.backlog))
		LOG_F(ERROR, "Failed to listen on %s:%i", opt.host, opt.port);

	const std::string usedHost = mSocket->GetServerAddr();
	const int usedPort = mSocket->GetServerPort();
	mResponseAdr = new RemoteCommunicator(usedHost, usedPort, Protocol::TCP);
	LOG_INFO("Server listening on %s", mResponseAdr->toString().c_str());

	while (mAlive) {
		CActiveSocket* client = NULL;
		if ((client = mSocket->Accept()) != NULL)
		{
			mWorkerThreads.push_back(new TCPClientWorker(this, mMessageHandler, client));
		}
	}
}

void TCPServer::stopClient(TCPClientWorker* worker) {
	for (auto it = mWorkerThreads.begin(); it != mWorkerThreads.end(); ++it) {
		if ((*it) == worker) {
			LOG_INFO("Stopping Client: %s:%i", worker->adr, worker->port);
			mWorkerThreads.erase(it);
			delete worker;//deletes client and joins thread
			break;
		}
	}
}





//////////////////////// TCPConnection ////////////////////////////






TCPConnection::TCPConnection(RemoteCommunicator* target, Options* opt, int idx)
	: AbstractConnection(idx),
	mSocket(new CActiveSocket()), mOptions(opt)
{
	mSocket->Initialize();
	//non-blocking?
	CHECK_F(mSocket->Open(target->host.c_str(), target->port), "Failed to open socket to %s:%i", target->host.c_str(), target->port);
}
TCPConnection::~TCPConnection()
{
	if (mSocket != NULL)
		close();
}

void TCPConnection::close() {
	mSocket->Close();
	delete mSocket;
	mSocket = NULL;
}

bool TCPConnection::send(net::OutMsg& msg) {
	msg.requestAcknowledge(mOptions->requestAcknowledge);

	int bytesSend = mSocket->Send((const uint8*)&msg.mBuffer[0], msg.mBuffer.size());
	if (bytesSend == 0) {
		LOG_F(WARNING, "The TCP socket to %s:%i has been closed by the remote side", mSocket->GetClientAddr(), mSocket->GetClientPort());
		return false;
	}
	else if (bytesSend == -1) {
		LOG_F(WARNING, "an unspecified error occures at connection: %s:%i", mSocket->GetClientAddr(), mSocket->GetClientPort());
		return false;
	}
	if (mOptions->requestAcknowledge) {
		int receivedBytes = mSocket->Receive(4);
		if (receivedBytes != 4) {
			LOG_F(WARNING, "An error occured while receiving acknowledge at %s:%i", mSocket->GetClientAddr(), mSocket->GetClientPort());
			return false;
		}
		return msg.checkAcknowledge((int8*)mSocket->GetData());
	}
	return true;

}