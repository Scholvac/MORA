/*
 * MoraIdentifier.cpp
 *
 *  Created on: 22.02.2020
 *      Author: sschweigert
 */

#include <MoraIdentifier.h>
#include <MoraUtils.h>
#include <MoraException.h>
#include <MoraStreams.h>

#include <stdlib.h>
#include <sstream>
using namespace mora;


RemoteCommunicator::RemoteCommunicator(const std::string h, int32 p, Protocol pr)
	: mHost{ h }, mPort{ p }, mProtocol{ pr }
{}
RemoteCommunicator::RemoteCommunicator(const RemoteCommunicator& o)
	: mHost{ o.mHost }, mPort{ o.mPort }, mProtocol{ o.mProtocol }, mQualifiedIdentifier{ o.mQualifiedIdentifier }
{}

RemoteCommunicator& RemoteCommunicator::operator=(RemoteCommunicator o) {
	swap(*this, o);
	return *this;
}


void RemoteCommunicator::fromQualifiedAddress(const std::string& qa) {
	size_t idx = qa.find("//");
	if (idx < 0) throw qa + " is an invalid address for a remote communicator";
	size_t idx2 = qa.find(":", idx);
	if (idx2 < 0) throw qa + " is an invalid address for a remote communicator";
	mProtocol = MoraUtils::toProtocol(qa.substr(0, idx - 1));
	mHost = qa.substr(idx + 2, idx2 - (idx + 2));
	mPort = std::atoi(qa.substr(idx2 + 1).c_str());
	mQualifiedIdentifier = qa;
}


std::string buildQualified(const RemoteCommunicator* rc) {
	if (rc->isValid())
	{
		std::stringstream ss;
		ss << MoraUtils::toString(rc->protocol()) << "://" << rc->host() << ":" << rc->port();
		return ss.str();
	}
	return std::string();
}
const std::string& RemoteCommunicator::qualifiedIdentifier() {
	if (mQualifiedIdentifier.empty()) {
		mQualifiedIdentifier = buildQualified(this);
	}
	return mQualifiedIdentifier;
}

const std::string RemoteCommunicator::qualifiedIdentifier() const {
	if (mQualifiedIdentifier.empty()) {
		return buildQualified(this);
	}
	return mQualifiedIdentifier;
}

void RemoteCommunicator::compile() {
	if (mQualifiedIdentifier.empty()) {
		mQualifiedIdentifier = buildQualified(this);
	}
}

//////////////////////////////////////////////////////////////////////////////////////////////////
//									RemoteObject												//
//////////////////////////////////////////////////////////////////////////////////////////////////

RemoteObject::RemoteObject(RemoteCommunicator remoteCommunicator, mora::IdentityType objectIdentity)
	: mRemoteCommunicator{ remoteCommunicator }, mObjectIdentity{ objectIdentity }
{
}
RemoteObject::RemoteObject(const RemoteObject& other) 
	: mRemoteCommunicator{ other.mRemoteCommunicator }, mObjectIdentity{ other.mObjectIdentity }
{
}

RemoteObject& RemoteObject::operator=(RemoteObject o) {
	swap(*this, o);
	return *this;
}


void RemoteObject::fromQualifiedRemoteIdentifier(const std::string& qadr) {
	//expect something like: <PROTOCOL>://<HOST>:<PORT>/<OBJECT_IDENTITY>
	size_t idx = qadr.rfind("/");
	if (idx < 0)
		throw MoraException("Could not seperate identifier");
	mRemoteCommunicator.fromQualifiedAddress(qadr.substr(0, idx) );
	mObjectIdentity = qadr.substr(idx + 1);
	mQualifiedIdentifier = qadr;
}

std::string buildQualified(const RemoteObject* rm) {
	if (rm->isValid())
	{
		std::stringstream ss;
		ss << rm->remoteCommunicator().qualifiedIdentifier() << "/" << rm->objectIdentity();
		return ss.str();
	}
	return std::string();
}
const std::string& RemoteObject::qualifiedIdentifier() {
	if (mQualifiedIdentifier.empty()) {
		mQualifiedIdentifier = buildQualified(this);
	}
	return mQualifiedIdentifier;
}
const std::string RemoteObject::qualifiedIdentifier() const {
	if (mQualifiedIdentifier.empty()) {
		return buildQualified(this);
	}
	return mQualifiedIdentifier;
}

void RemoteObject::compile() {
	mRemoteCommunicator.compile();
	if (mQualifiedIdentifier.empty()) {
		mQualifiedIdentifier = buildQualified(this);
	}
}

//////////////////////////////////////////////////////////////////////////////////////////////////
//									RemoteMethod												//
//////////////////////////////////////////////////////////////////////////////////////////////////

RemoteMethod::RemoteMethod(RemoteObject remoteObject, mora::SignatureType signature)
	: mRemoteObject{ remoteObject }, mSignature{signature}
{}
RemoteMethod::RemoteMethod(const RemoteMethod& other)
	: mRemoteObject{ other.mRemoteObject }, mSignature{ other.mSignature }, mQualifiedIdentifier{ other.mQualifiedIdentifier }, mQualifiedIdentifierAsBytes{ other.mQualifiedIdentifierAsBytes }
{}

RemoteMethod& RemoteMethod::operator=(RemoteMethod o) {
	swap(*this, o);
	return *this; 
}

const std::string buildStreamContent(const RemoteMethod* m) {
	if (m->isValid()) {
		std::stringstream ss;
		ss << m->remoteObject().objectIdentity() << ":" << m->signature();
		return ss.str();
	}
	return std::string{};
}
const std::string buildQualified(const RemoteMethod* m) {
	if (m->isValid()) {
		std::stringstream ss;
		ss << m->remoteObject().qualifiedIdentifier() << ":" << m->signature();
		return ss.str();
	}
	return std::string{};
}
const std::string RemoteMethod::qualifiedIdentifier() const{
	if (mQualifiedIdentifier.empty()) {
		return buildQualified(this);
	}
	return mQualifiedIdentifier;
}
const std::string& RemoteMethod::qualifiedIdentifier() {
	if (mQualifiedIdentifier.empty()) {
		mQualifiedIdentifier = buildQualified(this);
	}
	return mQualifiedIdentifier;
}


void RemoteMethod::writeAddressToStream(mora::OutputStream& stream) const {
	if (mQualifiedIdentifierAsBytes.empty() == false) {
		stream.append(mQualifiedIdentifierAsBytes);
	}else {
		//we have to do this manually since we can not write the qualifiedIdentifierAsByte 
		stream << buildStreamContent(this);
	}
}


void RemoteMethod::compile() {
	mRemoteObject.compile();
	if (mQualifiedIdentifierAsBytes.empty()) {
		const std::string& qn = buildStreamContent(this);
		MoraUtils::writeString(mQualifiedIdentifierAsBytes, 0, qn);
	}
}





namespace mora{
	void swap(RemoteCommunicator& first, RemoteCommunicator& second) {
		std::swap(first.mHost, second.mHost);
		std::swap(first.mPort, second.mPort);
		std::swap(first.mProtocol, second.mProtocol);
		std::swap(first.mQualifiedIdentifier, second.mQualifiedIdentifier);
	}

	void swap(RemoteObject& first, RemoteObject& second) {
		swap(first.mRemoteCommunicator, second.mRemoteCommunicator);
		std::swap(first.mObjectIdentity, second.mObjectIdentity);
		std::swap(first.mQualifiedIdentifier, second.mQualifiedIdentifier);
	}

	void swap(RemoteMethod& first, RemoteMethod& second) {
		swap(first.mRemoteObject, second.mRemoteObject);
		std::swap(first.mSignature, second.mSignature);
		std::swap(first.mQualifiedIdentifier, second.mQualifiedIdentifier);
		std::swap(first.mQualifiedIdentifierAsBytes, second.mQualifiedIdentifierAsBytes);
	}
}
