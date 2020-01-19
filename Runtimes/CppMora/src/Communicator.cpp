

#include "internal/precomp.h" //for precompiled headers

#include "MORA.h"
#include "MORAGen.h"
#include "MORAExt.h"

#include "internal/ConnectionManager.h"
#include "internal/MessageHandler.h"
#include "internal/MoraMessages.h"

#include <ThreadPool.h>
#include <loguru.hpp>

#include <map>


using namespace mora;


struct Communicator::CommunicatorData {
	Options									options;
	IServer*								server				{ NULL };
	MessageHandler*							messageHandler		{ NULL };
	ConnectionManager*						connections			{ NULL };
	ThreadPool*								threadPool			{ NULL };
	int16									magicCounter		{ 0 };
	std::map<std::string, IAdapterPtr>		adapter;
	std::map<std::string, IProxyPtr>		proxies;
	std::map<int16, IRemoteMethodCall*>		pendingCalls;

	CommunicatorData(const Options& opt)
		: options {opt}
	{
	}
};

#define mOptions data->options
#define mServer data->server
#define mThreadPool data->threadPool
#define mConnections data->connections
#define mAdapter data->adapter
#define mProxies data->proxies
#define mPendingCalls data->pendingCalls
#define mMessageHandler data->messageHandler




Communicator::Communicator(const Options& options) 
	:	data{new CommunicatorData(options)}
{
}

Communicator::~Communicator()
{
	if (mServer) //indicates that start has been called before
		stop();
	delete data;
}


bool Communicator::start() {
	CHECK_F(mServer == NULL, "Communicator has already been started");

	mThreadPool = new ThreadPool(mOptions.workerThreads);
	mThreadPool->init();
	LOG_DEBUG("Initialized Threadpool with %i threads", mOptions.workerThreads);

	mConnections = new ConnectionManager(&mOptions);
	mMessageHandler = new MessageHandler(this, mThreadPool, mConnections);
	IServer* server = ConnectionFactory::createServer(mOptions.protocol);

	CHECK_NOTNULL_F(server, "Failed to create sever for procotol %s", MoraUtils::toString(mOptions.protocol).c_str());
	if (server->startServer(this, mMessageHandler)) {
		mServer = server;
		RemoteCommunicator* rc = mServer->getResponseAddress();
	} else {
		LOG_F(ERROR, "Failed to start the server");
		ConnectionFactory::destroyServer(server);
		SAFE_DELETE(mMessageHandler);
		SAFE_DELETE(mThreadPool);
		return false;
	}
	return true;
}
bool Communicator::stop() {
	if (mServer) {
		if (mServer->stopServer()) {
			ConnectionFactory::destroyServer(mServer); //even if this fails - we can not do anything...
			mServer = NULL; 
			LOG_INFO("server stopped");
		}
		else {
			LOG_WARN("Failed to stop server");
			return false;
		}
		
		mThreadPool->shutdown();
		mThreadPool = NULL;

		SAFE_DELETE(mConnections);

	}
	return true;
}
bool Communicator::waitForTermination() {
	throw "Not yet implemented";
	return false;
}


bool Communicator::call(IRemoteMethodCall* call) {
	return mMessageHandler->call(call);
}

OutMethodCall* Communicator::createCall(RemoteMethod* target, int16 magic) {
	return new OutMethodCall(magic, target, mServer->getResponseAddress());
}




const Options& Communicator::getOptions() const {
	return mOptions;
}
const RemoteCommunicator* Communicator::getResponseAddress() const {
	return mServer != NULL ? mServer->getResponseAddress() : NULL;
}

IAdapterPtr Communicator::registerAdapter(IAdapterPtr adapter) {
	const std::string& identifier = adapter->getIdentifier();
	CHECK_F(hasAdapter(identifier) == false, "An Adapter with identifier %s is already registered", identifier.c_str());
	mAdapter.insert(std::make_pair(identifier, adapter));
	return adapter;
}
IAdapterPtr Communicator::removeAdapter(const std::string& identifier) {
	auto it = mAdapter.find(identifier);
	if (it != mAdapter.end())
		return (mAdapter.erase(it))->second;
	return IAdapterPtr{ NULL };
}
bool Communicator::hasAdapter(const std::string& identifier) {
	return mAdapter.find(identifier) != mAdapter.end();
}
IAdapterPtr Communicator::getAdapter(const std::string& identifier) {
	auto it = mAdapter.find(identifier);
	if (it != mAdapter.end())
		return it->second;
	return IAdapterPtr{ NULL };
}
IAdapterPtr Communicator::getAdapter(void* adapterImpl) {
	for (auto it = mAdapter.begin(); it != mAdapter.end(); ++it)
		if (it->second->represents(adapterImpl))
			return it->second;
	return IAdapterPtr{ NULL };
}

IProxyPtr Communicator::registerProxy(IProxyPtr proxy) {
	const std::string& identifier = proxy->getQualifiedAddress();
	CHECK_F(hasProxy(identifier) == false, "An proxy with address %s is already registered", identifier.c_str());
	mProxies.insert(std::make_pair(identifier, proxy));
	return proxy;
}
IProxyPtr Communicator::removeProxy(const std::string& identifier) {
	auto it = mProxies.find(identifier);
	if (it != mProxies.end())
		return mProxies.erase(it)->second;
	return IProxyPtr{ NULL };
}
bool Communicator::hasProxy(const std::string& identifier) {
	return mProxies.find(identifier) != mProxies.end();
}
IProxyPtr Communicator::getProxy(const std::string& identifier) {
	auto it = mProxies.find(identifier);
	if (it != mProxies.end())
		return it->second;
	return IProxyPtr{ NULL };
}