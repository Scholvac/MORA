#ifndef CONNECTION_MANAGER_H_
#define CONNECTION_MANAGER_H_

#include <MORA.h>
#include <map>
#include <string>


class ThreadPool;

namespace mora {
	

	class OutMessage;
	class AbstractConnection;

	class ConnectionStack {
	private:
		RemoteCommunicator					target;
		Options* options;
		std::vector<AbstractConnection*>	connections;
	public:
		ConnectionStack(RemoteCommunicator* target, Options* options);
		~ConnectionStack();

		AbstractConnection* getConnection();
		void release(AbstractConnection* con);

		void close();
	};

	class ConnectionManager {
		using ConnectionMap = std::map<std::string, ConnectionStack*>;
	private:
		Options*			mOptions			{NULL};
		ConnectionMap		mConnectionStacks;
	public:
		ConnectionManager(Options* options);
		~ConnectionManager();

		bool send(const OutMessage& msg);
	};
}


#endif //CONNECTION_MANAGER_H_
