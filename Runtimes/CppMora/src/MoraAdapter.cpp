/*
 * Adapter.cpp
 *
 *  Created on: 22.02.2020
 *      Author: sschweigert
 */

#include "LogDef.h"
#include <MoraAdapter.h>

#include <Poco/Format.h>

using namespace mora;



MethodNotFoundException::MethodNotFoundException(const mora::TypeIdentifier& adapterType, const mora::IdentityType& identity, const mora::SignatureType& signature)
	: AdapterException(Poco::format("Method %s in type %s not found (identity=%s)", signature.c_str(), adapterType.c_str(), identity.c_str()).c_str())
{
}

Adapter::Adapter(DelegatePtr ptr, const mora::RemoteObject& identity, const mora::TypeIdentifier& type, const InvokerFunctionMap& handler)
	: MoraObject{ identity, type, false }, mDelegate{ ptr }, mInvokerFunctions{ handler }
{
	
}

Adapter::~Adapter() {
	//nothing to delete here
}

bool Adapter::represents(void* ptr) const {
	return ptr == mDelegate.get();
}

void Adapter::invoke(IRemoteMethodCall& context) {
	LOG_TRACE("Start invokation of method %s::%s", mIdentity.qualifiedIdentifier().c_str(), context.signature.c_str());
	
	InvokerFunctionMap::const_iterator it = mInvokerFunctions.find(context.signature());
	if (it != mInvokerFunctions.end()) {
		it->second(mDelegate.get(), context);
	}
	else {
		throw MethodNotFoundException(type(), identity().objectIdentity(), context.signature());
	}

	LOG_TRACE("Finish invokation of method %s::%s", mIdentity.qualifiedIdentifier().c_str(), context.signature.c_str());
}
