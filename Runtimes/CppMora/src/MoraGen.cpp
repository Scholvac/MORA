
#include "internal/precomp.h"
#include "MORAGen.h"

#include "internal/MoraMessages.h"

#include <sstream>

using namespace mora;




IAdapter::IAdapter(Communicator* communicator, const std::string& identifier)
	:	mCommunicator{communicator}, mIdentifier{identifier}
{
	
}
IAdapter::~IAdapter() {

}

const std::string& IAdapter::getIdentifier() {
	return  mIdentifier;
}
const std::string& IAdapter::getQualifiedIdentifier() {
	if (mQualifiedIdentifier.empty()) {
		std::stringstream ss;
		ss << mCommunicator->getResponseAddress()->toString().c_str();
		ss << "/" << mIdentifier;
		mQualifiedIdentifier = ss.str();
	}
	return mQualifiedIdentifier;
}





IProxy::IProxy(){}
IProxy::~IProxy() {}

Proxy::Proxy(Communicator* com, RemoteObject ro)
	: mCommunicator(com), mRemoteObject(ro)
{

}

Proxy::~Proxy()
{
	for (auto it = mMethods.begin(); it != mMethods.end(); ++it)
		delete it->second;
}

const std::string& Proxy::getQualifiedAddress() {
	return mRemoteObject.toString();
}

RemoteMethod* Proxy::getMethod(const std::string& sig)
{
	auto it = mMethods.find(sig);
	if (it == mMethods.end()) {
		RemoteMethod* rm = new RemoteMethod(mRemoteObject, sig);
		mMethods.insert(std::make_pair(sig, rm));
		return rm;
	}
	return it->second;
}


class Proxy_CheckType : public IRemoteMethodCall {
private:
	std::promise<std::string>	promise;
public:
	Proxy_CheckType(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod)
		: ::mora::IRemoteMethodCall(communicator, targetMethod)
	{
	}
	virtual ~Proxy_CheckType()
	{
	}

	std::future<std::string> invoke() {
		//Do the encoding - in this case there are no parameter
		send();
		return promise.get_future();
	}
	void handleResult(::mora::InputStream& response) {
		std::string type;
		response >> type;
		promise.set_value(type);
	}
};

bool Proxy::checkType(const std::string& typeName)
{
	Proxy_CheckType* pct = new Proxy_CheckType(mCommunicator, getMethod("_getType_"));
	std::future<std::string> future = pct->invoke();

	std::string result = future.get();
	return result == typeName;
}