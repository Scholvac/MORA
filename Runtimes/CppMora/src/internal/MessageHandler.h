#ifndef MESSAGE_HANDLER_H_
#define MESSAGE_HANDLER_H_

#include <MORAExt.h>
#include <map>

class ThreadPool;

namespace mora {

	class InMethodResponse;
	class IRemoteMethodCall;
	class OutMethodResponse;
	class ConnectionManager;

	class MessageHandler : public IMessageHandler{
		using PendingCallsMap = std::map<int16, IRemoteMethodCall*>;
	private:
		Communicator*			mCommunicator;
		ConnectionManager*		mConnectionManager;
		ThreadPool*				mThreadPool;
		PendingCallsMap			mPendingCalls;
	public:
		MessageHandler(Communicator* com, ThreadPool* tp, ConnectionManager* cmgr);
		virtual ~MessageHandler();


		void handleIncommingMessage(mora::net::InMsgCollection* msg);
		bool sendResponse(OutMethodResponse& resp);
		bool call(IRemoteMethodCall* call);
	};

}

#endif //MESSAGE_HANDLER_H_