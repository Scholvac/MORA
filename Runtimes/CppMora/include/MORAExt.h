/*
 * MORAExt.h
 *
 */
#ifndef MORA_EXT_H_ 
#define MORA_EXT_H_

#include <MORA.h>

namespace mora {


	namespace net {
		class InMsg {
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

		class InMsgCollection {
		private:
			int8				magic;
			std::vector<InMsg*>	messages;
			std::string			responseAdr;
			int					messageCounter;
			InputStream* stream;
		public:
			InMsgCollection(InMsg* msg, const std::string& responseAdr);
			~InMsgCollection();

			void append(InMsg* msg);
			bool isComplete();

			InputStream& getStream() const { return *stream; }
			const std::string& getResponseAdr() { return responseAdr; }
		};

		class OutMsg {
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




	class AbstractConnection {
	public:
		class IConnectionProvider {
		public:
			IConnectionProvider() {}
			virtual ~IConnectionProvider() {}

			virtual AbstractConnection* createConnection(RemoteCommunicator* com, Options* opt, int idx) = 0;
			virtual bool destroyConnection(AbstractConnection* connection) = 0;
		};
	public:
		int idx;
		bool used;
	public:
		AbstractConnection(int index) : used(false), idx(index) {}
		virtual ~AbstractConnection() {}

		virtual Protocol getProtocol() const = 0;
		virtual int32 getMaximumMessageSize() const = 0;

	public:
		virtual bool send(net::OutMsg& msg) = 0;
		virtual void close() = 0;
	};


	class IMessageHandler {
	public:
		IMessageHandler() {}
		virtual ~IMessageHandler() {}

	public:
		virtual void handleIncommingMessage(mora::net::InMsgCollection* msg) = 0;
	};

	class IServer {
	public:
		class IServerProvider {
		public:
			IServerProvider() {}
			virtual ~IServerProvider() {}

			/** creates a new sever instance. 
			* The instance shall not yet be started */
			virtual IServer* createServer() = 0;
			virtual bool destroyServer(IServer* server) = 0;
		};
	public:
		IServer() {}
		virtual ~IServer() {}
	public:
		/** returns the address on which other Communicator could find this server. 
		* This this address will also be added to each send message, in order to tell the proxy whom to response. */
		virtual RemoteCommunicator* getResponseAddress() = 0;
		/** starts listening for new messages. 
		*	The server shall use (as much as possible) the options, accessible through the communicator. 
		*	Received messages shall be forwarded to the message handler */
		virtual bool startServer(Communicator* communicator, IMessageHandler* handler) = 0;
		/** Tells the server to stop listening for new messages. 
		* This includes also, closing of all open connections. */
		virtual bool stopServer() = 0;
	};

	
	
	class ConnectionFactory {
	public: //registration methods
		static void registerServerProvider(const Protocol protocol, std::shared_ptr<IServer::IServerProvider> provider);
		static std::shared_ptr<IServer::IServerProvider> removeServerProvider(const Protocol protocol);

		static void registerConnectionProvider(const Protocol protocol, std::shared_ptr<AbstractConnection::IConnectionProvider> provider);
		static std::shared_ptr<AbstractConnection::IConnectionProvider> removeConnectionProvider(const Protocol protocol);
	public://query methods
		static IServer* createServer(const Protocol protocol);
		static bool destroyServer(IServer* server);

		static AbstractConnection* createConnection(RemoteCommunicator* target, Options* options);
		static bool destroyConnection(AbstractConnection* connection);
	};
}


#endif /* MORA_EXT_H_ */
