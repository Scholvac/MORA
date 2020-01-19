#ifndef MORA_MESSAGES_H_
#define MORA_MESSAGES_H_

#include <MORAExt.h>
#include <MORAGen.h>
#include <MoraStreams.h>

#include <vector>

namespace mora {
	
	const int8			RESPONSE_STATUS__EXCEPTION = 1;


	const int8 			MESSAGE_TYPE__METHOD_CALL = 1;
	const int8 			MESSAGE_TYPE__RESPONSE = 2;
	const int8 			MESSAGE_TYPE__TOPIC = 4;

	

	class MoraMessage {
	public:
		const int16		magic;
		const int8		messageType;
	protected:
		MoraMessage(int16 mag, int8 mt)
			:	magic(mag), messageType(mt)
		{}
		~MoraMessage() {}
	};
	class InMessage : public MoraMessage {
	private:
		net::InMsgCollection* networkMessage;
	protected:
		InMessage(int16 mag, int8 mt, net::InMsgCollection* c);
		virtual ~InMessage();
	public:
		inline InputStream& stream() { return networkMessage->getStream(); }
	};
	class InMethodCall : public InMessage {
	private:
		RemoteCommunicator*	responseCommunicator;
		std::string			objectIdentifier;
		std::string			methodSignature;
	public:
		InMethodCall(int16 magic, int8 type, net::InMsgCollection* mc);
		~InMethodCall();

		RemoteCommunicator* getResponseCommunicator() { return responseCommunicator; }
		const std::string& getObjectIdentifier()const { return objectIdentifier; }
		const std::string& getMethodSignature() const { return methodSignature; }

	};
	class InMethodResponse : public InMessage {
	public:
		int8			statusFlag;
		std::string		errorMessage;
	public:
		InMethodResponse(int16 magic, int type, net::InMsgCollection* mc);
		~InMethodResponse();
	};
	

	class OutMessage : public MoraMessage {
	public:
		RemoteCommunicator*		target;
		OutputStream			stream;
	public:
		OutMessage(int16 mag, int8 mt, RemoteCommunicator* com);
		virtual ~OutMessage();		
	};

	class OutMethodCall : public OutMessage {
	public:
		const RemoteCommunicator*	responseAdr;
		RemoteMethod*				target;
	public:
		OutMethodCall(int16 magic, RemoteMethod* target, const RemoteCommunicator* responseAdr);
		virtual ~OutMethodCall();
	};

	class OutMethodResponse : public OutMessage {
	public:
		OutMethodResponse(InMethodCall* call, const char* errorMsg=NULL);
		~OutMethodResponse();
	};
}


#endif //MORA_MESSAGES_H_c