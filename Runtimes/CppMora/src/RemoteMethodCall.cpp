
#include "internal/precomp.h"
#include "MORAGen.h"

#include "internal/MoraMessages.h"

using namespace mora;

IRemoteMethodCall::IRemoteMethodCall(Communicator* com, RemoteMethod* rm)
	: mTargetMethod{ rm }, mCommunicator{com},
	mMethodCall{NULL}
{
	mMethodCall = mCommunicator->createCall(mTargetMethod, ::mora::MoraUtils::generateShortMagic());
}

IRemoteMethodCall::~IRemoteMethodCall()
{
	if (mMethodCall != NULL) {
		delete mMethodCall;
		mMethodCall = NULL;
	}
}

OutputStream& IRemoteMethodCall::getParameterStream() {
	return mMethodCall->stream;
}

void IRemoteMethodCall::send() {
	mCommunicator->call(this);
}
