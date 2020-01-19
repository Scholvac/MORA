
#include "internal/precomp.h"
#include "internal/Encoding.h"

#include "MORA.h"
#include "MORAGen.h"



using namespace mora;


RemoteCommunicator::RemoteCommunicator(std::string h, int32 po, Protocol pr)
	: host(h), port(po), protocol(pr)
{}
RemoteCommunicator::RemoteCommunicator(const RemoteCommunicator& copy)
	: host(copy.host), port(copy.port), protocol(copy.protocol)
{}
RemoteCommunicator::~RemoteCommunicator()
{
}

const std::string& RemoteCommunicator::toString() {
	if (qualifiedAdr.empty())
		qualifiedAdr = MoraUtils::toString(protocol) + "://" + host + ":" + std::to_string(port);
	return qualifiedAdr;
}

const std::string RemoteCommunicator::toString() const {
	if (qualifiedAdr.empty())
		return MoraUtils::toString(protocol) + "://" + host + ":" + std::to_string(port);
	return qualifiedAdr;
}

RemoteCommunicator RemoteCommunicator::create(const std::string& qa) {
	size_t idx = qa.find("//");
	if (idx < 0) throw qa + " is an invalid address for a remote communicator";
	size_t idx2 = qa.find(":", idx);
	if (idx2 < 0) throw qa + " is an invalid address for a remote communicator";
	std::string prot = qa.substr(0, idx - 1);
	std::string host = qa.substr(idx + 2, idx2 - (idx + 2));
	std::string port = qa.substr(idx2 + 1);
	return RemoteCommunicator(host, std::atoi(port.data()), MoraUtils::toProtocol(prot));
}



RemoteObject::RemoteObject(const std::string& id, RemoteCommunicator com)
	: objectIdentifier(id), communicator(com)
{}
RemoteObject::~RemoteObject() {}

const std::string& RemoteObject::toString()
{
	if (mQualifiedAddress.empty()) {
		mQualifiedAddress = communicator.toString() + "/" + objectIdentifier;
	}
	return mQualifiedAddress;
}

RemoteObject RemoteObject::create(const std::string& qadr)
{
	size_t idx = qadr.rfind("/");
	CHECK_GE_F(idx, 0, "Could not seperate identifier");
	return RemoteObject(qadr.substr(idx + 1), RemoteCommunicator::create(qadr.substr(0, idx)));
}



RemoteMethod::RemoteMethod(RemoteObject ro, const std::string& sig)
	: remoteObject(ro), methodSignature(sig)
{
}

RemoteMethod::~RemoteMethod()
{}


const std::vector<int8>& RemoteMethod::getStreamContent() {
	if (mStreamContent.empty()) {
		std::string str = remoteObject.objectIdentifier + ":" + methodSignature;
		mStreamContent.resize(4 + 2 * str.size());
		Encoding::writeString(&mStreamContent[0], 0, str);
	}
	return mStreamContent;
}