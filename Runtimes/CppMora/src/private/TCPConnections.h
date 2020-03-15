/*
 * TCPConnections.h
 *
 *  Created on: 22.02.2020
 *      Author: sschweigert
 */

#ifndef SRC_PRIVATE_TCPCONNECTIONS_H_
#define SRC_PRIVATE_TCPCONNECTIONS_H_

#include "MoraIdentifier.h"
#include "MoraTransport.h"

#include "Poco/Net/ServerSocket.h"
#include "Poco/Net/SocketReactor.h"
#include "Poco/Net/SocketAcceptor.h"
#include "Poco/Net/StreamSocket.h"
#include "Poco/Thread.h"


namespace mora {

	
	class TCPSocketWorker;
	class TCPServer : public IServer {
	private:
		Poco::Net::ServerSocket						mServerSocket;
		Poco::Net::SocketReactor					mReactor;
		Poco::Thread								mReactorThread;
		Poco::Net::SocketAcceptor<TCPSocketWorker>*	mAcceptor;
	public:
		TCPServer(Communicator& communicator, MessageHandler& handler);
		virtual ~TCPServer();
	};

	class TCPConnection : public AbstractConnection {
	private:
		Poco::Net::StreamSocket		mSocket;
		std::vector<int8>			mAckMsg;
	public:
		TCPConnection(const RemoteCommunicator& target, const Options& options);
		virtual ~TCPConnection();

		virtual int32 getMaximumMessageSize() const { return mOptions.maxTCPMessageSize; }
	public:
		virtual bool send(net::OutMsg& msg);
		virtual void close();
	};

} /* namespace mora */

#endif /* SRC_PRIVATE_TCPCONNECTIONS_H_ */
