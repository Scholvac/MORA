/*
 * MoraRemoteMethodCall.cpp
 *
 *  Created on: 23.02.2020
 *      Author: sschweigert
 */

#include <MoraRemoteMethodCall.h>
#include <MoraUtils.h>

#include "private/MoraTransport.h"

using namespace mora;

IRemoteMethodCall::IRemoteMethodCall(const RemoteMethod& rm, int16 magic, InputStream* in, Communicator& communicator) 
	: mTargetMethod{ rm }, mCommunicator{ communicator }, mMagic{ magic }, mInputStream{ in }
{
	mOutputStream << MESSAGE_TYPE__RESPONSE;
	mOutputStream << mMagic;
}
IRemoteMethodCall::IRemoteMethodCall(const RemoteMethod& rm, Communicator& com)
	: mTargetMethod{ rm }, mCommunicator{ com }, mInputStream{ nullptr }, mMagic{MoraUtils::generateShortMagic()}
{
	mOutputStream << MESSAGE_TYPE__METHOD_CALL;
	mOutputStream << mMagic;
	const RemoteCommunicator& rc = rm.remoteObject().remoteCommunicator();
	mOutputStream << MoraUtils::toByte(rc.protocol());
	mOutputStream << com.responsePort();

	rm.writeAddressToStream(mOutputStream);
}

IRemoteMethodCall::~IRemoteMethodCall() {
	mMagic = -1;
}

const std::string IRemoteMethodCall::qualifiedTarget() const {
	return mTargetMethod.remoteObject().qualifiedIdentifier() + ":" + signature();
}

bool IRemoteMethodCall::isValid() const {
	return mTargetMethod.isValid();
}

const SignatureType IRemoteMethodCall::signature() const {
	return mTargetMethod.signature();
}

void IRemoteMethodCall::handleResponse(InputStream* stream) {
	mInputStream = stream;
	int8 errFlag;
	(*stream) >> errFlag;
	if (errFlag == 0)
		handleResponse();
	else {
		LOG_ERROR("Received error");
	}
}