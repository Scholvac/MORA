/*
 * MORAGen.h
 *
 */
#ifndef MORA_GEN_H_
#define MORA_GEN_H_

#include <MORA.h>
#include <MoraStreams.h>

#include <map>
#include <string>
#include <future>
#include <thread>

namespace mora {

	class OutMethodCall;
	class InMethodResponse;

	

	class RemoteMethod {
	private:
		std::vector<int8>			mStreamContent;
	public:
		RemoteObject				remoteObject;
		std::string					methodSignature;
	public:
		RemoteMethod(RemoteObject remoteObject, const std::string& signature);
		~RemoteMethod();

		const std::vector<int8>& getStreamContent();
	};

	class IRemoteMethodCall {
	private:
		RemoteMethod* mTargetMethod;
		OutMethodCall* mMethodCall;
	protected:
		Communicator* mCommunicator;
	public:
		IRemoteMethodCall(Communicator* communicator, RemoteMethod* method);
		virtual ~IRemoteMethodCall();

		OutMethodCall& getMessage() { return *mMethodCall; }

	protected:
		OutputStream& getParameterStream();
		void send();
	public:
		virtual void handleResult(InputStream& response) = 0;
	};



	template <typename IFACE>
	class Adapter : public IAdapter {
	public:
		typedef void (*InvokerFuncPtr)(IFACE*, ::mora::InputStream&, ::mora::OutputStream&, ::mora::Communicator* com);
		typedef std::map<std::string, InvokerFuncPtr> InvokerFunctionMap;
	protected:
		std::shared_ptr<IFACE>	mDelegate;
	public:
		Adapter(Communicator* com, std::shared_ptr<IFACE> iface, const std::string& identifier)
			:	IAdapter(com, identifier),
				mDelegate{ iface }
		{
		}
		virtual ~Adapter()
		{}

		bool represents(void* delegate) {
			return mDelegate.get() == delegate;
		}

		void invoke(const std::string& signature, ::mora::InputStream& inStream, ::mora::OutputStream& outStream, ::mora::Communicator* com) {
			InvokerFunctionMap& funcMap = getInvokerFunctionMap();
			auto funcIt = funcMap.find(signature);
			if (funcIt == funcMap.end()) {
				LOG_F(ERROR, "Could not find a method with signature %s in object %s of type %s", signature.c_str(), getIdentifier().c_str(), getTypeIdentifier().c_str());
			}
			(funcIt->second)(mDelegate.get(), inStream, outStream, mCommunicator);
		}

		virtual const std::string getTypeIdentifier() const = 0;
		virtual InvokerFunctionMap& getInvokerFunctionMap() = 0;

	};


	class Proxy : public IProxy{
	private:
		RemoteObject							mRemoteObject;
		Communicator*							mCommunicator;
		std::map<std::string, RemoteMethod*>	mMethods;
	protected:
		RemoteMethod* getMethod(const std::string& name);
	public:
		Proxy(Communicator* communicator, RemoteObject remoteObject);
		virtual ~Proxy();

		bool checkType(const std::string& typeName);

		virtual const std::string& getQualifiedAddress();
		Communicator* getCommunicator() { return mCommunicator; }

	};
}


#endif /* MORA_GEN_H_ */
