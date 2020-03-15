/*
 * Proxy.h
 *
 *  Created on: 22.02.2020
 *      Author: sschweigert
 */

#ifndef INCLUDE_MORAPROXY_H_
#define INCLUDE_MORAPROXY_H_

#include <MoraPreReq.h>
#include <MoraStreams.h>
#include <MoraIdentifier.h>
#include <MoraException.h>
#include <MoraAdapter.h>

#include <unordered_map>

namespace mora {

	class Communicator;

	using MethodMap = std::unordered_map<SignatureType, RemoteMethod*>;

	
	
	
	class ProxyException : public MoraException {
	public:
		ProxyException(const char* msg) : MoraException(msg) {};
		virtual ~ProxyException() = default;
	};

	class MORA_API Proxy : public MoraObject {
	private:
		MethodMap			mMethodMap;
		Communicator&		mCommunicator;
	public:
		Proxy(RemoteObject remoteObject, const mora::TypeIdentifier& type, Communicator& communicator);
		virtual ~Proxy();

		bool checkType(const mora::TypeIdentifier& expectedType);
		void call(IRemoteMethodCall* remoteMethodCall);
	public:
		const RemoteMethod& getMethod(const ::mora::SignatureType& signature);

		Communicator& communicator() { return mCommunicator; }

		::mora::duration& timeout() const;
	};

} /* namespace mora */

#endif /* INCLUDE_MORAPROXY_H_ */
