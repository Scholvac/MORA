/*
 * Adapter.h
 *
 *  Created on: 22.02.2020
 *      Author: sschweigert
 */

#ifndef INCLUDE_MORAADAPTER_H_
#define INCLUDE_MORAADAPTER_H_

#include <MoraPreReq.h>
#include <MoraException.h>
#include <MoraStreams.h>
#include <MoraIdentifier.h>
#include <MoraRemoteMethodCall.h>
#include <MoraObject.h>


#include <memory>
#include <unordered_map>

namespace mora {
	

	class AdapterException : public MoraException {
	public:
		AdapterException(const char* msg) : MoraException(msg) {};
		virtual ~AdapterException() = default;
	};
	class MethodNotFoundException : public AdapterException {
	public:
		MethodNotFoundException(const mora::TypeIdentifier& adapterType, const mora::IdentityType& identity, const mora::SignatureType& signature);
		virtual ~MethodNotFoundException() = default;
	};

	class Communicator;

	class MORA_API Adapter : public MoraObject {
		friend class Communicator;
	public:
		typedef void (*InvokerFuncPtr)(void*, IRemoteMethodCall&);
		typedef std::unordered_map<mora::SignatureType, InvokerFuncPtr> InvokerFunctionMap;
		typedef std::shared_ptr<void>	DelegatePtr;
	protected:
		/** pointer to the actual logic object */
		DelegatePtr					mDelegate;

		/** A map of methods to be called when a remote call arives for this instance*/
		const InvokerFunctionMap& mInvokerFunctions;
	public:
		Adapter(DelegatePtr ptr, const mora::RemoteObject& identity, const mora::TypeIdentifier& type, const InvokerFunctionMap& handler);
		virtual ~Adapter();

		
		/** returns true, if the adapter points to the same delegate as the void* ptr */
		bool represents(void* ptr) const;

		/** Handles incomming methods invokations */
		void invoke(IRemoteMethodCall& context);

		DelegatePtr getDelegate() { return mDelegate; }
	};



} /* namespace mora */

#endif /* INCLUDE_MORAADAPTER_H_ */
