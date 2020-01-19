/*
 * MessageHandler.cpp
 *
 *  Created on: 19.01.2020
 *      Author: sschweigert
 */


#include "internal/precomp.h"

#include "MoraStreams.h"

#include "internal/MessageHandler.h"
#include "internal/MoraMessages.h"
#include "internal/ConnectionManager.h"

#include "ThreadPool.h"

using namespace mora;

MessageHandler::MessageHandler(Communicator* com, ThreadPool* tp, ConnectionManager* cmgr)
	: mCommunicator{ com }, mThreadPool{ tp }, mConnectionManager{ cmgr }
{

}
MessageHandler::~MessageHandler() {

}





void handleResponse(InMethodResponse* resp, std::map<int16, IRemoteMethodCall*>* pendingCalls, MessageHandler* handler) {
	try {
		auto it = pendingCalls->find(resp->magic);
		if (it == pendingCalls->end())
			LOG_F(ERROR, "Could not find call for response (magic: %i)", resp->magic);
		else {
			IRemoteMethodCall* originalCall = it->second;
			pendingCalls->erase(it);
			originalCall->handleResult(resp->stream());
			delete originalCall; //do not delete here, that is handled by the caller
		}
	}
	catch (std::exception const& e) {
		LOG_F(WARNING, "Failed to handle method response with error: %s", e.what());
	}
	delete resp;
}
void handleMethodCall(InMethodCall* call, MessageHandler* msgHandler, Communicator* com) {
	try {
		IAdapterPtr adapter = com->getAdapter(call->getObjectIdentifier());
		CHECK_F(adapter != nullptr, "Could not find an Adapter for identifier %s", call->getObjectIdentifier().c_str());

		OutMethodResponse resp(call);
		adapter->invoke(call->getMethodSignature(), call->stream(), resp.stream, com);
		msgHandler->sendResponse(resp);
	}
	catch (std::exception const& e) {
		LOG_F(WARNING, "%s", e.what());
		OutMethodResponse resp(call, e.what());
		msgHandler->sendResponse(resp);
	}
	catch (...) {
		std::string err = "Unkown Exception " + call->getObjectIdentifier() + ":" + call->getMethodSignature();
		LOG_F(WARNING, "%s", err.c_str());
		OutMethodResponse resp(call, err.c_str());
		msgHandler->sendResponse(resp);
	}

	delete call;
}
void MessageHandler::handleIncommingMessage(mora::net::InMsgCollection* msg)
{
	InputStream& stream = msg->getStream();
	int8 flags;
	stream >> flags;
	int16 magic;
	stream >> magic;

	if ((flags & MESSAGE_TYPE__RESPONSE) == MESSAGE_TYPE__RESPONSE) {
		InMethodResponse* resp = new InMethodResponse(magic, flags, msg);
		auto future = mThreadPool->submit(handleResponse, resp, &mPendingCalls, this);
	}
	else if ((flags && MESSAGE_TYPE__METHOD_CALL) == MESSAGE_TYPE__METHOD_CALL) {
		InMethodCall* call = new InMethodCall(magic, flags, msg); //will be deleted by the handler
		auto future = mThreadPool->submit(handleMethodCall, call, this, mCommunicator);
	}
}

bool MessageHandler::sendResponse(OutMethodResponse& resp) {
	return mConnectionManager->send(resp);
}

bool MessageHandler::call(IRemoteMethodCall* call) {
	OutMethodCall& msg = call->getMessage();
	mPendingCalls.insert(std::make_pair(msg.magic, call));
	return mConnectionManager->send(msg);
}