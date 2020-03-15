/*
 * Proxy.cpp
 *
 *  Created on: 22.02.2020
 *      Author: sschweigert
 */

#include "MoraProxy.h"
#include "MoraCommunicator.h"

#include <Poco/Format.h>

#include <future>

using namespace mora;






Proxy::Proxy(RemoteObject robj, const TypeIdentifier& type, Communicator& com)
	: MoraObject{ robj, type, true}, mCommunicator(com)
{
}

Proxy::~Proxy() {
	while (!mMethodMap.empty()) {
		RemoteMethod* rm = mMethodMap.begin()->second;
		auto it = mMethodMap.erase(mMethodMap.begin());
		delete rm;
	}
}

duration& Proxy::timeout() const {
	return mCommunicator.timeout();
}

const RemoteMethod& Proxy::getMethod(const SignatureType& sig) {
	MethodMap::iterator it = mMethodMap.find(sig);
	if (it == mMethodMap.end()) {
		RemoteMethod* rm = new RemoteMethod{ mIdentity, sig };
		rm->compile(); //ensure that it is done only once - while we do have non-const access
		it = mMethodMap.insert(std::make_pair(sig, rm)).first;
	}
	return *it->second;
}

class CheckTypeRMC : public IRemoteMethodCall {
public:
	std::promise<std::string>	promise;

	CheckTypeRMC(const ::mora::RemoteMethod& targetMethod, mora::Communicator& com)
		: ::mora::IRemoteMethodCall(targetMethod, com)
	{}
	virtual ~CheckTypeRMC() = default;

	virtual void handleResponse() {
		std::string _result;
		responseInStream() >> _result;
		promise.set_value(_result);
	}
};
bool Proxy::checkType(const mora::TypeIdentifier& expectedType) {
	CheckTypeRMC* rmc = new CheckTypeRMC(getMethod("_getType_"), communicator());
	call(rmc);

	std::future<std::string> future = rmc->promise.get_future();
	std::future_status status = future.wait_for(timeout());
	if (status != std::future_status::ready)
		return false;
	std::string type = future.get();
	return type == expectedType;
}
void Proxy::call(IRemoteMethodCall* rmc) {
	try {
		mCommunicator.call(rmc);
	}
	catch (MoraException me) {
		throw me;
	}
	catch (std::exception exp) {
		throw MoraException(exp.what());
	}
	catch (...) {
		throw MoraException("Unknown error");
	}
}
