/*
 * TCPConnections.cpp
 *
 *  Created on: 22.02.2020
 *      Author: sschweigert
 */

#include "MoraPreReq.h"
#include "private/TCPConnections.h"
#include "private/MessageHandler.h"

#include "Poco/FIFOBuffer.h"
#include "Poco/NObserver.h"
#include "Poco/Delegate.h"
#include "Poco/Observer.h"

using namespace mora;
using namespace mora::net;
using namespace Poco;
using namespace Poco::Net;

#define INITIAL_BUFFER_SIZE 1024


using InMsgCollectionMap = std::map<mora::int8, net::InMsgCollection*>;

namespace mora {
	class TCPSocketWorker {
	public:
		Poco::Net::StreamSocket		mSocket;
		Poco::Net::SocketReactor&	mReactor;
		MessageHandler*				mHandler;

		net::InMsg*				msg{ new InMsg{INITIAL_BUFFER_SIZE} };
		mora::int8				ack_buffer[4];
		InMsgCollectionMap		collections;
		std::string				externHost;

		TCPSocketWorker(Poco::Net::StreamSocket& socket, Poco::Net::SocketReactor& reactor, MessageHandler* mh = nullptr)
			: mSocket{ socket }, mReactor{ reactor }, mHandler{ mh }
		{
			reactor.addEventHandler(socket, NObserver<TCPSocketWorker, ReadableNotification>(*this, &TCPSocketWorker::onSocketReadable));
			reactor.addEventHandler(socket, NObserver<TCPSocketWorker, ShutdownNotification>(*this, &TCPSocketWorker::onSocketShutdown));
			externHost = mSocket.address().host().toString();
		}

		void onSocketReadable(const AutoPtr<ReadableNotification>& pNf) {
			try {
				int bytesRead = mSocket.receiveBytes(msg->getInsertBuffer(), msg->getRemainingCapcity());
				if (bytesRead > 0) {
					if (msg->handleNextBytes(bytesRead)) {
						handleFinishedMessage(msg);
						msg = new net::InMsg(INITIAL_BUFFER_SIZE);
					}
				}
			}
			catch (Poco::Exception & e)
			{
				// This is where the "Connection reset by peer" lands
				LOG_ERROR("Catched Exception: what = %s", e.what());
				mSocket.close();
				delete this;
			}
		}
		void handleFinishedMessage(net::InMsg* msg) {
			if (msg->requiresAcknowledge(&(ack_buffer[0]))) {
				mSocket.sendBytes(ack_buffer, 4);
			}
			net::InMsgCollection* coll = NULL;
			std::map<mora::int8, net::InMsgCollection*>::iterator it = collections.end();

			if (msg->numberOfMessages == 1) {
				coll = new net::InMsgCollection(msg, externHost);
			}
			else {
				it = collections.find(msg->magic);
				if (it == collections.end()) {
					collections.insert(std::pair<mora::int8, net::InMsgCollection*>(msg->magic, coll = new net::InMsgCollection(msg, externHost)));
				}
				else {
					coll = it->second;
					coll->append(msg);
				}
			}
			if (coll == NULL)
				throw std::runtime_error("Could not determine MessageCollection");

			if (coll->isComplete()) {
				if (it != collections.end())
					collections.erase(it);
				mHandler->handleIncommingMessage(coll);
			}
		}
		void onSocketShutdown(const AutoPtr<ShutdownNotification>& pNf)
		{
			delete this;
		}
	};
}
class TCPAcceptor : public Poco::Net::SocketAcceptor<TCPSocketWorker> {
public:
	MessageHandler& mHandler;
	TCPAcceptor(ServerSocket& socket, SocketReactor& reactor, MessageHandler& handler) : SocketAcceptor(socket, reactor), mHandler{ handler } {
		
	}
	virtual ~TCPAcceptor() = default;

protected:
	virtual TCPSocketWorker* createServiceHandler(StreamSocket& socket) {
		return new TCPSocketWorker(socket, *reactor(), &mHandler);
	}
};


TCPServer::TCPServer(Communicator& communicator, MessageHandler& handler)
	: IServer(communicator, handler)
{
	Poco::Net::SocketAddress address{ communicator.options().host, (Poco::UInt16)communicator.options().port };
	mServerSocket = Poco::Net::ServerSocket(address, communicator.options().backlog);
	mReactor.setTimeout(Poco::Timespan(0, 250));
	mAcceptor = new TCPAcceptor(mServerSocket, mReactor, handler);
	
	mReactorThread.start(mReactor);
	
	mSelfAddress = RemoteCommunicator(mServerSocket.address().host().toString(), mServerSocket.address().port(), Protocol::TCP);
}
TCPServer::~TCPServer()
{
	mReactor.stop();
	mReactorThread.join(300);
	mServerSocket.close();
	SAVE_DELETE(mAcceptor);
}












TCPConnection::TCPConnection(const RemoteCommunicator& target, const Options& options)
	:	AbstractConnection(target, options),
		mSocket{ Poco::Net::SocketAddress{target.host(), (Poco::UInt16)target.port()} },
		mAckMsg(4,0)
{

}

TCPConnection::~TCPConnection()
{

}

bool TCPConnection::send(net::OutMsg& msg) {
	msg.requestAcknowledge(mOptions.requestAcknowledge);

	int bytesSend = mSocket.sendBytes(&msg.mBuffer[0], (int)msg.mBuffer.size());
	if (bytesSend != msg.mBuffer.size())
		return false;

	if (mOptions.requestAcknowledge) {
		int recBytes = mSocket.receiveBytes(&mAckMsg[0], 4);
		if (recBytes != 4) {
			LOG_WARN("Received unknown acknowledge message");
			return false;
		}
		return msg.checkAcknowledge(&mAckMsg[0]);
	}

	return true;//TODO: this may not all, what if not all bytes have been send, e.g. bytesSend > 0 && bytesSend < msg.mBuffer.size()
}

void TCPConnection::close() {
	mSocket.close();
}