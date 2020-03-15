/*
 * MessageHandler.h
 *
 *  Created on: 23.02.2020
 *      Author: sschweigert
 */

#ifndef SRC_PRIVATE_MESSAGEHANDLER_H_
#define SRC_PRIVATE_MESSAGEHANDLER_H_

#include "MoraPreReq.h"
#include "MoraTransport.h"

#include <Poco/ThreadPool.h>

#include <unordered_map>

namespace mora {

	class Communicator;
	using PendingCallMap = std::unordered_map<int16, IRemoteMethodCall*>;

	class MessageHandler {
	private:
		Communicator& 		mCommunicator;
		PendingCallMap&		mPendingCalls;
		Poco::ThreadPool	mThreadPool;
	public:
		MessageHandler(Communicator& communicator, PendingCallMap& pendingCalls);
		virtual ~MessageHandler();

		void handleIncommingMessage(mora::net::InMsgCollection* msg);
	};

} /* namespace mora */

#endif /* SRC_PRIVATE_MESSAGEHANDLER_H_ */
