
#include "internal/precomp.h"
#include "internal/MoraMessages.h"

using namespace mora;




InMessage::InMessage(int16 magic, int8 type, net::InMsgCollection* mc)
	: MoraMessage(magic, type),
	networkMessage(mc)
{}
InMessage::~InMessage() {
	delete networkMessage;
}

InMethodCall::InMethodCall(int16 magic, int8 type, net::InMsgCollection* mc)
	: InMessage(magic, type, mc)
{
	InputStream& stream = mc->getStream();
	int8 prot;
	stream >> prot;
	int32 port;
	stream >> port;
	responseCommunicator = new RemoteCommunicator(mc->getResponseAdr(), port, MoraUtils::toProtocol(prot));

	std::string ident;
	stream >> ident;
	size_t idx = ident.find(':');
	if (idx <= 0)
		throw "Invalid MethodCall - wrong identifier: " + ident;
	objectIdentifier = ident.substr(0, idx);
	methodSignature = ident.substr(idx + 1);
}

InMethodCall::~InMethodCall() {
	delete responseCommunicator;
}








InMethodResponse::InMethodResponse(int16 magic, int type, net::InMsgCollection* mc)
	: InMessage(magic, type, mc), statusFlag(-1)
{
	stream() >> statusFlag;
	if ((statusFlag & RESPONSE_STATUS__EXCEPTION) == RESPONSE_STATUS__EXCEPTION) {
		stream() >> errorMessage;
	}
}
InMethodResponse::~InMethodResponse()
{

}








OutMessage::OutMessage(int16 mag, int8 mt, RemoteCommunicator* com)
	: MoraMessage(mag, mt), target(com)
{
	stream << mt;
	stream << mag;
}
OutMessage::~OutMessage()
{
}



OutMethodCall::OutMethodCall(int16 magic, RemoteMethod* t, const RemoteCommunicator* resp)
	: OutMessage(magic, MESSAGE_TYPE__METHOD_CALL, &t->remoteObject.communicator),
	target(t), responseAdr(resp)
{
	stream << MoraUtils::toByte(resp->protocol);
	stream << resp->port;

	stream.append(t->getStreamContent());
}

OutMethodCall::~OutMethodCall()
{
}

OutMethodResponse::OutMethodResponse(InMethodCall* call, const char* errorMsg)
	: OutMessage(call->magic, MESSAGE_TYPE__RESPONSE, call->getResponseCommunicator())
{
	if (errorMsg != NULL) {
		stream << RESPONSE_STATUS__EXCEPTION;
		stream << std::string(errorMsg);
	}
	else {
		stream << (int8)0;
	}
}

OutMethodResponse::~OutMethodResponse() {
	//do not delete the response communicator - we did not create it
	target = NULL;
}