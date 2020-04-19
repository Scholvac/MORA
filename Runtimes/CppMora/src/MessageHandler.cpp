/*
 * MessageHandler.cpp
 *
 *  Created on: 23.02.2020
 *      Author: sschweigert
 */
#include "LogDef.h"
#include "private/MessageHandler.h"

#include "MoraRemoteMethodCall.h"
#include "MoraCommunicator.h"
#include "MoraAdapter.h"
#include "MoraUtils.h"

#include "Poco/Format.h"

using namespace mora;
using namespace mora::net;

MessageHandler::MessageHandler(Communicator& com, PendingCallMap& pcm)
	: mCommunicator{ com }, mPendingCalls{ pcm }, mThreadPool{ "MessageHandlerPool", 1, com.options().workerThreads }
{
}

MessageHandler::~MessageHandler() {
	// TODO Auto-generated destructor stub
}

namespace mora {
	class IncommingMethodCall : public IRemoteMethodCall {
	public:
		IncommingMethodCall(RemoteMethod& rm, int16 magic, InputStream* in, Communicator& communicator)
			: IRemoteMethodCall(rm, magic, in, communicator)
		{
			mOutputStream << (int8)0; //indicate that we have no error
		}
		virtual ~IncommingMethodCall() = default;

		virtual bool isResponse() { return true; }
		virtual void handleResponse() {} //we do not expect a response but reuse the IRemoteMethodCall

		void setError(const std::string err) {
			//if we have an error we do replace the output stream
			mOutputStream = mora::OutputStream{};
			mOutputStream << MESSAGE_TYPE__RESPONSE;
			mOutputStream << mMagic;
			mOutputStream << RESPONSE_STATUS__EXCEPTION;
			mOutputStream << err;
		}

	};
}

class IncommingCallHandler : public Poco::Runnable {
public:
	InMsgCollection*	mMsg;
	int16				mMagic;
	int8				mFlags;
	Communicator&		mCommunicator;

	IncommingCallHandler(mora::net::InMsgCollection* msg, int16 magic, int8 flags, Communicator& com)
		: mMsg{ msg }, mMagic{ magic }, mFlags{ flags }, mCommunicator(com)
	{}
	~IncommingCallHandler() = default;


	virtual void run() {

		InputStream& stream = mMsg->getStream();
		int8 respProtocol;
		stream >> respProtocol;
		int32 respPort;
		stream >> respPort;
		const std::string& respAdr = mMsg->getResponseAdr();

		std::string ident;
		stream >> ident;
		size_t idx = ident.find(':');
		if (idx <= 0)
			throw "Invalid MethodCall - wrong identifier: " + ident;
		std::string objectIdentity = ident.substr(0, idx);
		std::string signature = ident.substr(idx + 1);

		AdapterPtr adapter = mCommunicator.getAdapter(objectIdentity);
		if (!adapter) {
			throw AdapterException(Poco::format("Could not find adapter: %s", objectIdentity.c_str()).c_str());
		}
		const RemoteCommunicator& respCom = mCommunicator.getRemoteCommunicator(respAdr, respPort, MoraUtils::toProtocol(respProtocol));
		RemoteMethod rm{ RemoteObject{respCom, objectIdentity}, signature };
		IncommingMethodCall call{ rm, mMagic, &stream, mCommunicator };

		try {
			adapter->invoke(call);
		}
		catch (std::exception exp) {
			LOG_WARN(Poco::format("Invoked method throws an Exception: %s", exp.what()).c_str());

			//TODO: send error method response
			call.setError(std::string{ exp.what() });
		}
		mCommunicator.call(&call);


		delete mMsg;
		delete this;
	}
};


class ResponseHandler : public Poco::Runnable {
public:
	InMsgCollection*	mMsg;
	int16				mMagic;
	int8				mFlags;
	PendingCallMap&		mPendingCalls;

	ResponseHandler(mora::net::InMsgCollection* msg, int16 magic, int8 flags, PendingCallMap& pcm)
		: mMsg{ msg }, mMagic{ magic }, mFlags{ flags }, mPendingCalls{ pcm }
	{}
	~ResponseHandler() = default;


	virtual void run() {

		auto pit = mPendingCalls.find(mMagic);
		if (pit != mPendingCalls.end()) {
			IRemoteMethodCall* pending_call = pit->second;
			mPendingCalls.erase(pit);
			
			pending_call->handleResponse(&mMsg->getStream());
		}
		else {
			LOG_ERROR(Poco::format("No pending call for magic: %i", mMagic).c_str());
		}
		

		delete mMsg;
		delete this;
	}
};

void MessageHandler::handleIncommingMessage(mora::net::InMsgCollection* msg)
{
	InputStream& stream = msg->getStream();
	int8 flags;
	stream >> flags;
	int16 magic;
	stream >> magic;

	if ((flags & MESSAGE_TYPE__RESPONSE) == MESSAGE_TYPE__RESPONSE) {
		mThreadPool.start(*(new ResponseHandler(msg, magic, flags, mPendingCalls)));
	}
	else if ((flags && MESSAGE_TYPE__METHOD_CALL) == MESSAGE_TYPE__METHOD_CALL) {
		mThreadPool.start(*(new IncommingCallHandler(msg, magic, flags, mCommunicator)));
	}
}