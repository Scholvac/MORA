/*
 * Communicator.cpp
 *
 *  Created on: 22.02.2020
 *      Author: sschweigert
 */

#include "MoraCommunicator.h"
#include "MoraAdapter.h"
#include "MoraProxy.h"
#include "MoraUtils.h"

#include "private/MessageHandler.h"
#include "private/MoraTransport.h"
#include "private/TCPConnections.h"
#include "private/UDPConnections.h"
#include "private/ConnectionManager.h"

#include <Poco/Format.h>

#include <unordered_map>

using namespace mora;


using AdapterMap = std::unordered_map<IdentityType, AdapterPtr>;
using ProxyMap = std::unordered_map<std::string, ProxyPtr>;
using RemoteCommunicatorMap = std::unordered_map<Protocol, std::unordered_map<std::string, std::unordered_map<int32, RemoteCommunicator*>>>;


struct Communicator::CommunicatorData {
	Options&				options;
	AdapterMap				adapterMap;
	ProxyMap				proxyMap;
	
	MessageHandler*			handler;
	IServer* 				server {nullptr};
	ConnectionManager		connectionMgr;

	PendingCallMap			pendingCalls;
	RemoteCommunicatorMap	remoteCommunicators;
	// valid after succsesfull �call to start()
	int32					_responsePort{ -1 };
	RemoteCommunicator		_selfAddress{};
	std::string				_selfAddressStr{};

	CommunicatorData(Options& opt) 
		: options{ opt }, connectionMgr{ opt }
	{
	}
	~CommunicatorData() {
		for (auto pit = remoteCommunicators.begin(); pit != remoteCommunicators.end(); ++pit) {
			for (auto ait = pit->second.begin(); ait != pit->second.end(); ++ait) {
				for (auto rit = ait->second.begin(); rit != ait->second.end(); ++rit)
					delete rit->second;
			}
		}
	}
};


Communicator::Communicator(Options& opt) 
	: data {new CommunicatorData{ opt } }
{
}

Communicator::~Communicator() {
	delete data;
}







bool Communicator::start(){
	if (isRunning())
		return false;

	data->handler = new MessageHandler(*this, data->pendingCalls);
	//create and start the server
	Communicator& com = *this;
	switch(options().protocol){
	case Protocol::TCP:
		data->server = new TCPServer(*this, *(data->handler));
		break;
	case Protocol::UDP:
		data->server = new UDPServer(*this, *(data->handler));
		break;
	}

	if (data->server != nullptr) {
		data->_selfAddress = data->server->getResponseAdresse();
		data->_responsePort = data->_selfAddress.port();
		data->_selfAddressStr = data->_selfAddress.qualifiedIdentifier();
		return true;
	}
	return false;
}


bool Communicator::stop(){
	if (data->server == nullptr)
		return false;
	SAVE_DELETE(data->server);
	return true;
}

bool Communicator::waitForTermination(){
	return false;
}
bool Communicator::isRunning(){
	return data->server != nullptr;
}









bool Communicator::registerAdapter(AdapterPtr adapter) {
	if (hasAdapter(adapter->identity().objectIdentity()))
		return false;
	
	bool res = data->adapterMap.insert(std::make_pair(adapter->identity().objectIdentity(), adapter)).second;
	if (res) {
		//adapter->identity().remoteCommunicator = getIdentity();
		return true;
	}
	return false;
}

bool Communicator::removeAdapter(const IdentityType& identity) {
	AdapterMap::iterator it = data->adapterMap.find(identity);
	if (it == data->adapterMap.end())
		return false;
	data->adapterMap.erase(it);
	return true;
}

bool Communicator::hasAdapter(const IdentityType& identity) const {
	return data->adapterMap.find(identity) != data->adapterMap.end();
}

AdapterPtr Communicator::getAdapter(const IdentityType& identity) const {
	AdapterMap::iterator it = data->adapterMap.find(identity);
	if (it == data->adapterMap.end())
		return AdapterPtr{ nullptr };
	return it->second;
}
AdapterPtr Communicator::getAdapter(void* ptr) const {
	for (AdapterMap::iterator it = data->adapterMap.begin(); it != data->adapterMap.end(); ++it) {
		if (it->second->represents(ptr))
			return it->second;
	}
	return AdapterPtr{ nullptr };
}



bool Communicator::registerProxy(ProxyPtr proxy) {
	RemoteObject identity = proxy->identity();
	const std::string& id = identity.qualifiedIdentifier();
	if (hasProxy(id))
		return false;

	return data->proxyMap.insert(std::make_pair(id, proxy)).second;
}

bool Communicator::hasProxy(const std::string& qa) const {
	return data->proxyMap.find(qa) != data->proxyMap.end();
}
bool Communicator::hasProxy(const RemoteObject& ro) const {
	std::string id = ro.objectIdentity();
	if (id.empty())
		return false;
	return hasProxy(id);
}

ProxyPtr Communicator::getProxy(const std::string& qa) const {
	ProxyMap::const_iterator it = data->proxyMap.find(qa);
	if (it == data->proxyMap.end())
		return ProxyPtr(nullptr);
	return it->second;
}

ProxyPtr Communicator::getProxy(const RemoteObject& ro) const {
	std::string id = ro.qualifiedIdentifier();
	return getProxy(id);
}

bool Communicator::removeProxy(const std::string& qa) {
	ProxyMap::const_iterator it = data->proxyMap.find(qa);
	if (it == data->proxyMap.end())
		return false;
	return data->proxyMap.erase(it) != data->proxyMap.end();
}

bool Communicator::removeProxy(const RemoteObject& ro) {
	return removeProxy(ro.qualifiedIdentifier());
}




MoraObjectPtr Communicator::getObject(const std::string& qid) const {
	auto pit = data->proxyMap.find(qid);
	if (pit != data->proxyMap.end())
		return pit->second;
	
	size_t idx = qid.find(data->_selfAddressStr.c_str());
	if (idx >= 0) {
		std::string identity = qid.substr(data->_selfAddressStr.length()+1);
		auto ait = data->adapterMap.find(identity);
		if (ait != data->adapterMap.end())
			return ait->second;
	}
	
	return MoraObjectPtr{};
}


int32 Communicator::responsePort() const { return data->_responsePort; }



Options& Communicator::options() { return data->options; }
const Options& Communicator::options() const { return data->options; }

duration& Communicator::timeout() const { return data->options.timeout; }

RemoteObject Communicator::createIdentity(const mora::IdentityType& identifier) {
	return RemoteObject{ data->_selfAddress, identifier };
}



void Communicator::call(IRemoteMethodCall* rmc) {
	if (!rmc->isValid())
		throw ProxyException(Poco::format("Try to call an invalid method %s", rmc->qualifiedTarget().c_str()).c_str());
	
	if (rmc->isResponse() == false) {
		auto it = data->pendingCalls.insert(std::make_pair(rmc->mMagic, rmc)).second;
		if (!data->connectionMgr.send(*rmc)) {
			data->pendingCalls.erase(it); //if we did not send it, we will not get a response
			throw ProxyException(Poco::format("Failed to send remote method invocation to: %s", rmc->qualifiedTarget().c_str()).c_str());
		}
	}
	else {
		if (!data->connectionMgr.send(*rmc)) { //just send without remember the call
			throw ProxyException(Poco::format("Failed to send remote method invocation to: %s", rmc->qualifiedTarget().c_str()).c_str());
		}
	}
}


const RemoteCommunicator& Communicator::getRemoteCommunicator(const std::string& adr, int32 port, Protocol prot) {
	auto pit = data->remoteCommunicators.find(prot);
	if (pit == data->remoteCommunicators.end()) {
		pit = data->remoteCommunicators.insert(std::make_pair(prot, std::unordered_map<std::string, std::unordered_map<int32, RemoteCommunicator*>>{})).first;
	}
	auto ait = pit->second.find(adr);
	if (ait == pit->second.end())
		ait = pit->second.insert(std::make_pair(adr, std::unordered_map<int32, RemoteCommunicator*>{})).first;
	auto rit = ait->second.find(port);
	if (rit == ait->second.end()) {
		RemoteCommunicator* rc = new RemoteCommunicator(adr, port, prot);
		rc->compile();
		rit = ait->second.insert(std::make_pair(port, rc)).first;
	}
	return *rit->second;
}
