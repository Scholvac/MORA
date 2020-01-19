/*
 * MORA.h
 *
 *  Created on: Nov 30, 2019
 *      Author: sschweigert
 */
#ifndef INCLUDE_MORA_H_ 
#define INCLUDE_MORA_H_

#include <inttypes.h>
#include <string>
#include <memory>
#include <map>
#include <vector>

namespace mora {

	typedef int8_t	int8;
	typedef int16_t int16;
	typedef int32_t int32;
	typedef int64_t int64;


	
	struct Options;

	class Communicator;
	class InputStream;
	class OutputStream;
	class IAdapter;
	class IProxy;
	class IRemoteMethodCall;
	class OutMethodCall;
	class RemoteMethod;

	using IAdapterPtr = std::shared_ptr<IAdapter>;
	using IProxyPtr = std::shared_ptr<IProxy>;


	enum class Protocol {
		TCP, UDP
	};
	struct Options {
		std::string		host						{ "localhost" };
		int32			port						{ 0 };
		Protocol		protocol					{ Protocol::TCP };

		int32			workerThreads				{ 5 };
		int32 			maxConnectionsPerClient		{ 5 };
		int32			backlog						{ 3000 };

		int64			timeout						{ 30 * 1000 };

		bool			requestAcknowledge			{ true };
		std::string 	name						{ "" };

		Options(Protocol _prot = Protocol::TCP, int _port = 0, std::string _host = "localhost")
			: host(_host), protocol(_prot), port(_port),
			workerThreads(5), maxConnectionsPerClient(5), backlog(3000),
			timeout(30 * 1000), requestAcknowledge(true), name("")
		{}
		Options() {}
		~Options() {}
	};

	class RemoteCommunicator {
	public:
		const std::string			host;
		const int32					port;
		const Protocol				protocol;
	private:
		std::string					qualifiedAdr;
	public:
		RemoteCommunicator(std::string host, int32 port, Protocol protocol);
		RemoteCommunicator(const RemoteCommunicator& copy);
		~RemoteCommunicator();

		static RemoteCommunicator create(const std::string& qualifiedAdr);
		const std::string& toString();
		const std::string toString() const;
	};

	class RemoteObject {
	private:
		std::string					mQualifiedAddress;
	public:
		RemoteCommunicator			communicator;
		std::string					objectIdentifier;

	public:
		RemoteObject(const std::string& id, RemoteCommunicator com);
		~RemoteObject();

		static RemoteObject create(const std::string& qualifiedAdr);
		const std::string& toString();
	};


	class IAdapter {
	private:
		std::string		mIdentifier;
		std::string		mQualifiedIdentifier;
	protected:
		Communicator*	mCommunicator;

	public:
		typedef void (*InvokerFuncPtr)(void*, ::mora::InputStream&, ::mora::OutputStream&, ::mora::Communicator* com);
	public:
		IAdapter(Communicator* communicator,  const std::string& identifier);
		virtual ~IAdapter();

		const std::string& getIdentifier();
		const std::string& getQualifiedIdentifier();

		virtual void invoke(const std::string& signature, ::mora::InputStream&, ::mora::OutputStream&, ::mora::Communicator* com) = 0;
		virtual bool represents(void* ptr) = 0;
	};

	class IProxy {
	public:
		IProxy();
		virtual ~IProxy();

		virtual const std::string& getQualifiedAddress() = 0;
	};
	

	class Communicator {
	private:
		struct CommunicatorData;
		CommunicatorData*	data;
	public:
		Communicator(const Options& options);
		~Communicator();

		bool start();
		bool stop();
		bool waitForTermination();

		const Options& getOptions() const;
		const RemoteCommunicator* getResponseAddress() const;

		IAdapterPtr registerAdapter(IAdapterPtr adapter);
		IAdapterPtr removeAdapter(const std::string& identifier);
		bool hasAdapter(const std::string& identifier);
		IAdapterPtr getAdapter(const std::string& identifier);
		IAdapterPtr getAdapter(void* adapterImpl);
		template<typename T>
		IAdapterPtr getAdapter(std::shared_ptr<T> delegate) {
			if (!delegate)
				return IAdapterPtr(NULL);
			void* vptr = (void*)delegate.get();
			return getAdapter(vptr);
		}

		IProxyPtr registerProxy(IProxyPtr proxy);
		IProxyPtr removeProxy(const std::string& identifier);
		bool hasProxy(const std::string& identifier);
		IProxyPtr getProxy(const std::string& identifier);

	public:
		bool call(IRemoteMethodCall* call);
		OutMethodCall* createCall(RemoteMethod* target, int16 magic);
	};


	class MoraUtils {
	public:
		static std::string createRandomIdentifier(int length = 5);
		static std::string toString(const Protocol prot);
		static int8 toByte(const Protocol prot);
		static Protocol toProtocol(const std::string& prot);
		static Protocol toProtocol(int8 prot);
		static int16 generateShortMagic();
	};
}


#endif /* INCLUDE_MORA_H_ */
