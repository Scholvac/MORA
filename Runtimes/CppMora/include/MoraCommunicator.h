/*
 * Communicator.h
 *
 *  Created on: 22.02.2020
 *      Author: sschweigert
 */

#ifndef COMMUNICATOR_H_
#define COMMUNICATOR_H_

#include "MoraPreReq.h"
#include "MoraIdentifier.h"

#include <string>
#include <memory>


namespace mora {

	
	struct Options {
		std::string				host						{ "127.0.0.1" };
		int32					port						{ -1 };
		Protocol				protocol					{ Protocol::TCP };

		int32					workerThreads				{ 5 };
		int32 					maxConnectionsPerClient		{ 5 };
		int32					backlog						{ 3000 };

		mora::duration 			timeout						{ 30 * 1000 };

		bool					requestAcknowledge			{ true };
		std::string 			name						{ "" };
		/** maximum size of a single TCP transmission (in bytes)
		 * @note This does not limit the size of the transfered
		 * data. If the data to be transfered exceedes the given size, the data 
		 * will be splitted into more messaages */
		int						maxTCPMessageSize			{ 10 * 1024 * 1024 };
		/** maximum size of a single UDP transmission (in bytes)
		 * @note This does not limit the size of the transfered
		 * data. If the data to be transfered exceedes the given size, the data
		 * will be splitted into more messaages 
		 * @warn this value is limited to 64000 bytes and will be cut automatically. 
		 */
		int						maxUDPMessageSize			{ 64000 };

		Options(Protocol _prot, int _port = -1, std::string _host = "localhost")
			: host(_host), port(_port), protocol(_prot),
			workerThreads(5), maxConnectionsPerClient(5), backlog(3000),
			timeout(30 * 1000), requestAcknowledge(true), name("")
		{}
		Options() {}
		~Options() {}
	};
	class MoraObject;
	using MoraObjectPtr = std::shared_ptr<MoraObject>;
	class Adapter;
	using AdapterPtr = std::shared_ptr<Adapter>;
	class Proxy;
	using ProxyPtr = std::shared_ptr<Proxy>;
	class IRemoteMethodCall;

	class MORA_API Communicator {
	private:
		struct CommunicatorData;
		CommunicatorData* data;
	public:
		Communicator(Options& options);
		virtual ~Communicator();

		//		State of communicator
		bool start();
		bool stop();
		bool waitForTermination();
		bool isRunning();

		//		adapters and proxies
		bool registerAdapter(AdapterPtr adapter);
		bool hasAdapter(const mora::IdentityType& identity) const;
		AdapterPtr getAdapter(void* impl) const;
		AdapterPtr getAdapter(const mora::IdentityType& identity) const;
		bool removeAdapter(const mora::IdentityType& identity);

		bool registerProxy(ProxyPtr proxy);
		bool hasProxy(const std::string& qualifiedAddress) const;
		bool hasProxy(const RemoteObject& remoteObject) const;
		ProxyPtr getProxy(const std::string& qualifiedAddress) const;
		ProxyPtr getProxy(const RemoteObject& remoteObject) const;
		bool removeProxy(const std::string& qualifiedAddress);
		bool removeProxy(const RemoteObject& remoteObject);

		MoraObjectPtr getObject(const std::string& qualifiedName) const;
	public:
		void call(IRemoteMethodCall* remoteMethodCall);
		int32 responsePort() const;
	public:
		Options& options();
		const Options& options() const;

		mora::duration& timeout() const;
		RemoteObject createIdentity(const mora::IdentityType& identifier);
		const RemoteCommunicator& getRemoteCommunicator(const std::string& adr, int32 port, Protocol prot);
	};

} /* namespace mora */

#endif /* COMMUNICATOR_H_ */
