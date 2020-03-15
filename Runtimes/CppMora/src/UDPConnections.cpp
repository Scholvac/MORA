/*
 * UDPConnections.cpp
 *
 *  Created on: 23.02.2020
 *      Author: sschweigert
 */
#include "MoraPreReq.h"
#include "private/UDPConnections.h"

using namespace mora;
using namespace mora::net;

UDPServer::UDPServer(Communicator& communicator, MessageHandler& handler)
	: IServer(communicator, handler)
{

}
UDPServer::~UDPServer()
{

}




UDPConnection::UDPConnection(const RemoteCommunicator& target, const Options& options)
	: AbstractConnection(target, options)
{

}

UDPConnection::~UDPConnection()
{

}

bool UDPConnection::send(net::OutMsg& msg) {
	return false;
}

void UDPConnection::close() {

}