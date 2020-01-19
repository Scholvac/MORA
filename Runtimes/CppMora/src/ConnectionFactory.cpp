/*
 * MessageHandler.cpp
 *
 *  Created on: 19.01.2020
 *      Author: sschweigert
 */



#include "internal/precomp.h"

#include <MORAExt.h>
#include "internal/TCPConnections.h"

#include <loguru.hpp>

#include <map>
#include <string>

using namespace mora;

using ServerProvider = std::shared_ptr<IServer::IServerProvider>;
using ServerProviderMap = std::map<Protocol, ServerProvider>;

using ConnectionProvider = std::shared_ptr<AbstractConnection::IConnectionProvider>;
using ConnectionProviderMap = std::map<Protocol, ConnectionProvider>;

static ConnectionProviderMap	sConnectionProviders;
static ServerProviderMap		sServerProviders;


static int initializeStandardConnections() {
	ConnectionFactory::registerServerProvider(Protocol::TCP, ServerProvider{ new TCPServerProvider } );
	ConnectionFactory::registerConnectionProvider(Protocol::TCP, ConnectionProvider{ new TCPConnectionProvider });
	return 0;
}
static int __dummy = initializeStandardConnections();

void ConnectionFactory::registerServerProvider(const Protocol protocol, ServerProvider provider){
	CHECK_F(sServerProviders.find(protocol) == sServerProviders.end(), "A server provider for protocol: %s has already been registered", MoraUtils::toString(protocol).c_str());
	sServerProviders.insert(std::make_pair(protocol, provider));
}
ServerProvider ConnectionFactory::removeServerProvider(const Protocol protocol) {
	auto& it = sServerProviders.find(protocol);
	if (it == sServerProviders.end())
		return ServerProvider{ NULL };
	return sServerProviders.erase(it)->second;
}

void ConnectionFactory::registerConnectionProvider(const Protocol protocol, ConnectionProvider provider) {
	CHECK_F(sConnectionProviders.find(protocol) == sConnectionProviders.end(), "A connection provider for protocol: %s has already been registered", MoraUtils::toString(protocol).c_str());
	sConnectionProviders.insert(std::make_pair(protocol, provider));
}
ConnectionProvider ConnectionFactory::removeConnectionProvider(const Protocol protocol) {
	auto& it = sConnectionProviders.find(protocol);
	if (it == sConnectionProviders.end())
		return ConnectionProvider{ NULL };
	return sConnectionProviders.erase(it)->second;
}

IServer* ConnectionFactory::createServer(const Protocol protocol) {
	auto& it = sServerProviders.find(protocol);
	if (it == sServerProviders.end()) {
		LOG_WARN("No server provider registered for protocol: %s", MoraUtils::toString(protocol).c_str());
		return NULL;
	}
	return it->second->createServer();
}
bool ConnectionFactory::destroyServer(IServer* server) {
	if (!server)
		return false;
	auto& it = sServerProviders.find(server->getResponseAddress()->protocol);
	if (it == sServerProviders.end()) {
		LOG_WARN("No server provider registered for protocol: %s", MoraUtils::toString(server->getResponseAddress()->protocol).c_str());
		return NULL;
	}
	return it->second->destroyServer(server);
}

AbstractConnection* ConnectionFactory::createConnection(RemoteCommunicator* target, Options* options) {
	CHECK_NOTNULL_F(target, "Missing target to connect to");
	auto& it = sConnectionProviders.find(target->protocol);
	if (it == sConnectionProviders.end()) {
		LOG_WARN("No server provider registered for protocol: %s", MoraUtils::toString(target->protocol).c_str());
		return NULL;
	}
	return it->second->createConnection(target, options, 0);
}
bool ConnectionFactory::destroyConnection(AbstractConnection* connection) {
	if (connection == NULL)
		return false;
	auto protocol = connection->getProtocol();
	auto& it = sConnectionProviders.find(protocol);
	if (it == sConnectionProviders.end()) {
		LOG_WARN("No server provider registered for protocol: %s", MoraUtils::toString(protocol).c_str());
		return NULL;
	}
	return it->second->destroyConnection(connection);

}
