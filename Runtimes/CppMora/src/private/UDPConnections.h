/*
 * UDPConnections.h
 *
 *  Created on: 23.02.2020
 *      Author: sschweigert
 */

#ifndef SRC_PRIVATE_UDPCONNECTIONS_H_
#define SRC_PRIVATE_UDPCONNECTIONS_H_

#include "MoraPreReq.h"
#include "MoraTransport.h"

namespace mora {

	#define MAX_UDP_MESSAGE_SIZE 64000

	class UDPServer : public IServer {
	public:
		UDPServer(Communicator& communicator, MessageHandler& handler);
		virtual ~UDPServer();
	};

	class UDPConnection : public AbstractConnection {
	public:
		UDPConnection(const RemoteCommunicator& target, const Options& options);
		virtual ~UDPConnection();

		virtual int32 getMaximumMessageSize() const { return (mOptions.maxUDPMessageSize < MAX_UDP_MESSAGE_SIZE) ? mOptions.maxUDPMessageSize : MAX_UDP_MESSAGE_SIZE; }
	public:
		virtual bool send(net::OutMsg& msg);
		virtual void close();
	};
} /* namespace mora */

#endif /* SRC_PRIVATE_UDPCONNECTIONS_H_ */
