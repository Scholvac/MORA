/*
 * MoraTransport.h
 *
 *  Created on: 22.02.2020
 *      Author: sschweigert
 */

#ifndef INCLUDE_MORATRANSPORT_H_
#define INCLUDE_MORATRANSPORT_H_

#include <MoraPreReq.h>
#include <MoraIdentifier.h>
#include <MoraCommunicator.h>
#include <MoraStreams.h>

#include <vector>

namespace mora {

	const int8			RESPONSE_STATUS__EXCEPTION = 1;


	const int8 			MESSAGE_TYPE__METHOD_CALL = 1;
	const int8 			MESSAGE_TYPE__RESPONSE = 2;
	const int8 			MESSAGE_TYPE__TOPIC = 4;


	

	namespace net {
		class MORA_API InMsg {
		public:
			int8				flags;
			int8				magic;
			int16				numberOfMessages;
			int16				messageNumber;
			int32				length;
			std::vector<int8>	data;

			int32				pos;
		public:
			InMsg(int capcity);
			~InMsg();

			int getRemainingCapcity();
			int8* getInsertBuffer();
			bool handleNextBytes(int bytesRead);

			bool requiresAcknowledge(int8* buffer);
		};

		class MORA_API InMsgCollection {
		private:
			int8				magic;
			std::vector<InMsg*>	messages;
			std::string			responseAdr;
			int					messageCounter;
			InputStream* stream;
		public:
			InMsgCollection(InMsg* msg, const std::string responseAdr);
			~InMsgCollection();

			void append(InMsg* msg);
			bool isComplete();

			InputStream& getStream() const { return *stream; }
			const std::string& getResponseAdr() { return responseAdr; }
		};

		class MORA_API OutMsg {
		public:
			std::vector<int8>	mBuffer;
		public:
			static std::vector<OutMsg> create(const OutputStream& stream, int maxMessageSize);
		public:
			OutMsg(int16 numberOfMessages, int16 messageNumber, int8 messageMagic, std::vector<int8>::const_iterator data_start, std::vector<int8>::const_iterator data_end, int32 length);
			~OutMsg();

			void requestAcknowledge(bool request);
			bool checkAcknowledge(int8* data);
		};
	}//Namespace net
	



	class Communicator;
	class MessageHandler;

	class MORA_API IServer {
	protected:
		Communicator& 			mCommunicator;
		MessageHandler&			mMessageHandler;
	protected:
		RemoteCommunicator 		mSelfAddress; //to be set by implementations
	public:
		IServer(Communicator& communicator, MessageHandler& handler);
		virtual ~IServer();

		const RemoteCommunicator& getResponseAdresse() const { return mSelfAddress; }
	};

	class MORA_API AbstractConnection {
	protected:
		const RemoteCommunicator&	mTarget;
		const Options&				mOptions;
	public:
		AbstractConnection(const RemoteCommunicator& target, const Options& options);
		virtual ~AbstractConnection();

		virtual Protocol getProtocol() const;
	public:
		virtual int32 getMaximumMessageSize() const = 0;
		virtual bool send(net::OutMsg& msg) = 0;
		virtual void close() = 0;
	};

} /* namespace mora */

#endif /* INCLUDE_MORATRANSPORT_H_ */
