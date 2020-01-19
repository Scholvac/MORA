#ifndef TCP_CONNECTIONS_H_
#define TCP_CONNECTIONS_H_

#include <MORAExt.h>


class CActiveSocket;
class CPassiveSocket;

namespace std{
	class thread;
}
namespace mora {

	class TCPClientWorker;
	class OutMsg;

	#define TCP_MAX_MESSAGE_SIZE 10*1024*1024

	class TCPServer : public IServer {
	private:
		CPassiveSocket*					mSocket				{ NULL };
		std::thread*					mAcceptorThread		{ NULL };
		bool							mAlive				{ true };
		std::vector<TCPClientWorker*>	mWorkerThreads;
		Communicator*					mCommunicator		{ NULL };
		IMessageHandler*				mMessageHandler		{ NULL };
		RemoteCommunicator*				mResponseAdr		{ NULL };
	public:
		TCPServer();
		~TCPServer();

		bool startServer(Communicator* com, IMessageHandler* handler);
		bool stopServer();

		Communicator* getCommunicator();
		RemoteCommunicator* getResponseAddress();
		void stopClient(TCPClientWorker* client);
	private:
		void listen(const Options& opt);
	};

	class TCPConnection : public AbstractConnection {
	private:
		CActiveSocket*					mSocket				{ NULL };
		Options*						mOptions			{ NULL };
	public:
		TCPConnection(RemoteCommunicator* com, Options* opt, int idx);
		~TCPConnection();

		virtual Protocol getProtocol() const { return Protocol::TCP;  }
		virtual int32 getMaximumMessageSize() const  { return TCP_MAX_MESSAGE_SIZE; }

		virtual bool send(net::OutMsg& msg);
		virtual void close();
	};

	class TCPServerProvider : public IServer::IServerProvider {
	public:
		virtual ~TCPServerProvider() {}

		virtual IServer* createServer() { return new TCPServer(); }
		virtual bool destroyServer(IServer* server) {
			if (server && dynamic_cast<TCPServer*>(server)) {
				delete server;
				return true;
			}
			return false;
		}
	};

	class TCPConnectionProvider : public AbstractConnection::IConnectionProvider {
	public:
		virtual ~TCPConnectionProvider() {}

		virtual AbstractConnection* createConnection(RemoteCommunicator* com, Options* opt, int idx) { return new TCPConnection(com, opt, idx); }
		virtual bool destroyConnection(AbstractConnection* connection) {
			if (connection && dynamic_cast<TCPConnection*>(connection)) {
				delete connection;
				return true;
			}
			return false;
		}
	};

	
}

#endif //TCP_CONNECTIONS_H_