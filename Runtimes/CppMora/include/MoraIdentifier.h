/*
 * MoraIdentifier.h
 *
 *  Created on: 22.02.2020
 *      Author: sschweigert
 */

#ifndef INCLUDE_MORAIDENTIFIER_H_
#define INCLUDE_MORAIDENTIFIER_H_


#include "MoraPreReq.h"

namespace mora {

	class OutputStream;

	enum class Protocol {
		TCP, UDP
	};

	class MORA_API RemoteCommunicator {
	private:
		std::string			mHost{""};
		int32				mPort{ -1 };
		Protocol			mProtocol{ Protocol::TCP };

		std::string			mQualifiedIdentifier;
	public:
		RemoteCommunicator() = default;
		RemoteCommunicator(const std::string host, int32 port, Protocol protocol);
		RemoteCommunicator(const RemoteCommunicator& other);
		RemoteCommunicator(const std::string& qualifiedAddress) { fromQualifiedAddress(qualifiedAddress); }
		~RemoteCommunicator() = default;

		RemoteCommunicator& operator=(RemoteCommunicator other);

		friend void swap(RemoteCommunicator& first, RemoteCommunicator& second);

		const std::string& host() const { return mHost; }
		int32 port() const { return mPort; }
		Protocol protocol() const { return mProtocol; }

		bool isValid() const { return mPort > 0; }

		void fromQualifiedAddress(const std::string& qualifiedAddress); //expect something like <PROTOCOL>://<HOST>:<PORT> for example: tcp://127.0.0.1:12345
		const std::string& qualifiedIdentifier();
		const std::string qualifiedIdentifier() const;

		void compile();
	};
	struct MORA_API RemoteObject {
	private:
		RemoteCommunicator	mRemoteCommunicator;
		mora::IdentityType	mObjectIdentity;

		std::string			mQualifiedIdentifier;
	public:
		RemoteObject(RemoteCommunicator remoteCommunicator, mora::IdentityType objectIdentity);
		RemoteObject(const std::string& qualifiedRemoteIdentifier) { fromQualifiedRemoteIdentifier(qualifiedRemoteIdentifier); }; //expect something like: <PROTOCOL>://<HOST>:<PORT>/<OBJECT_IDENTITY>
		RemoteObject(const RemoteObject& other);
		~RemoteObject() = default;

		RemoteObject& operator=(RemoteObject other);

		friend void swap(RemoteObject& first, RemoteObject& second);

		bool isValid() const { return mRemoteCommunicator.isValid() && mObjectIdentity.empty() == false; }

		RemoteCommunicator& remoteCommunicator() { return mRemoteCommunicator; }
		const RemoteCommunicator& remoteCommunicator() const { return mRemoteCommunicator; }

		mora::IdentityType& objectIdentity() { return mObjectIdentity; }
		const mora::IdentityType& objectIdentity() const { return mObjectIdentity; }

		void fromQualifiedRemoteIdentifier(const std::string& qualifiedRemoteIdentifier);
		const std::string& qualifiedIdentifier();
		const std::string qualifiedIdentifier() const;

		void compile();
	};
	class MORA_API RemoteMethod {
	private:
		RemoteObject		mRemoteObject;
		mora::SignatureType	mSignature;

		std::string			mQualifiedIdentifier;
		std::vector<int8>	mQualifiedIdentifierAsBytes;
	public:
		RemoteMethod(RemoteObject remoteObject, mora::SignatureType signature);
		RemoteMethod(const RemoteMethod& other); 
		~RemoteMethod() = default;

		RemoteMethod& operator=(RemoteMethod other);

		friend void swap(RemoteMethod& first, RemoteMethod& second);

		bool isValid() const { return mRemoteObject.isValid() && mSignature.empty() == false; }

		RemoteObject& remoteObject() { return mRemoteObject; }
		const RemoteObject& remoteObject() const { return mRemoteObject; }

		mora::SignatureType& signature() { return mSignature; }
		const mora::SignatureType& signature() const { return mSignature; }

		const std::string& qualifiedIdentifier();
		const std::string qualifiedIdentifier() const;

		/** builds the qualified identifier and the binary representation for faster access */
		void compile(); 
		/** writes the compiled address to the output stream, that includes (protocol, port, method). */
		void writeAddressToStream(mora::OutputStream& stream) const;

	};

} /* namespace mora */

#endif /* INCLUDE_MORAIDENTIFIER_H_ */
