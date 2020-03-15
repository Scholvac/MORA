/*
 * ConnectionManager.h
 *
 *  Created on: 24.02.2020
 *      Author: sschweigert
 */

#ifndef SRC_PRIVATE_CONNECTIONMANAGER_H_
#define SRC_PRIVATE_CONNECTIONMANAGER_H_

#include "MoraPreReq.h"
#include "MoraIdentifier.h"
#include "MoraCommunicator.h"

namespace mora {

	class IRemoteMethodCall;
	class AbstractConnection;


	class ConnectionStack {
		using ConnectionStore = std::pair<AbstractConnection*, bool>;
	private:
		RemoteCommunicator					mTarget;
		const Options&						mOptions;
		std::vector<ConnectionStore>		mConnections;
	public:
		ConnectionStack(RemoteCommunicator target, const Options& options);
		~ConnectionStack();

		AbstractConnection* getConnection();
		void release(AbstractConnection* con);

		void close();
	};

	class ConnectionManager {
		using ConnectionMap = std::map<std::string, ConnectionStack*>;
	private:
		const Options&		mOptions;
		ConnectionMap		mConnectionStacks;
	public:
		ConnectionManager(const Options& options);
		~ConnectionManager();

		bool send(const IRemoteMethodCall& msg);
	};

} /* namespace mora */

#endif /* SRC_PRIVATE_CONNECTIONMANAGER_H_ */
