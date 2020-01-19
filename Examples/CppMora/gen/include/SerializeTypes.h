#ifndef SERIALIZE_TYPES_H_
#define SERIALIZE_TYPES_H_


#include<MoraAdapter.h>
#include<MoraPreReq.h>
#include<MoraProxy.h>
#include<SerializeTypes.h>
#include<Streams.h>
#include<future>
#include<map>
#include<memory>
#include<vector>


namespace de{ namespace sos{ namespace mora{ namespace examples{ 

	////////////////////////////////////////////////////////////////////////////////
	//							Enumerations									  //
	////////////////////////////////////////////////////////////////////////////////
	enum MyEnum {
		KEY, 
		VALUE
	};
	
	class MyEnumUtil {
	public:
		static ::mora::int32 valueOf(const MyEnum value);
		static MyEnum valueOf(const ::mora::int32 value);
		
		static MyEnum read(::mora::InputStream& stream);
		static std::vector<MyEnum> readList(::mora::InputStream& stream);
		
		static void write(const MyEnum value, ::mora::OutputStream& stream);
		static void write(const std::vector<MyEnum>& value, ::mora::OutputStream& stream);
	};
	
	////////////////////////////////////////////////////////////////////////////////
	//								Structs										  //
	////////////////////////////////////////////////////////////////////////////////
	
	/********************* Pre Definition *********************************/
	struct SimpleStruct;
	struct ListStruct;
	
	/********************* Full Definition *********************************/
	struct SimpleStruct {
		bool boolValue;
		::mora::int8 byteValue;
		::mora::int16 shortValue;
		::mora::int32 intValue;
		::mora::int64 longValue;
		float floatValue;
		double doubleValue;
		std::string stringValue;
		MyEnum enumValue;
		SimpleStruct* structValue;
		
		SimpleStruct();			
		SimpleStruct(const SimpleStruct& copy);
		~SimpleStruct();
		
		
		static SimpleStruct* read(::mora::InputStream& stream);
		static std::vector<SimpleStruct*> readList(::mora::InputStream& stream);
		
		static void write(const SimpleStruct* value, ::mora::OutputStream& stream);
		static void write(const std::vector<SimpleStruct*>& value, ::mora::OutputStream& stream);
	};
	
	
	struct ListStruct {
		std::vector<bool> boolListValue;
		std::vector<::mora::int8> byteListValue;
		std::vector<::mora::int16> shortListValue;
		std::vector<::mora::int32> intListValue;
		std::vector<::mora::int64> longListValue;
		std::vector<float> floatListValue;
		std::vector<double> doubleListValue;
		std::vector<std::string> stringListValue;
		std::vector<MyEnum> enumListValue;
		std::vector<SimpleStruct*> structListValue;
		
		ListStruct();			
		ListStruct(const ListStruct& copy);
		~ListStruct();
		
		
		static ListStruct* read(::mora::InputStream& stream);
		static std::vector<ListStruct*> readList(::mora::InputStream& stream);
		
		static void write(const ListStruct* value, ::mora::OutputStream& stream);
		static void write(const std::vector<ListStruct*>& value, ::mora::OutputStream& stream);
	};
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////
	//								Interfaces										  //
	////////////////////////////////////////////////////////////////////////////////
	
	/********************* Pre Definition *********************************/
	class ICallback;
	typedef std::shared_ptr<ICallback> ICallbackPtr;
	class CallbackAdapter;
	class CallbackProxy;
	class IExtendedCallback;
	typedef std::shared_ptr<IExtendedCallback> IExtendedCallbackPtr;
	class ExtendedCallbackAdapter;
	class ExtendedCallbackProxy;
	class IEchoManager;
	typedef std::shared_ptr<IEchoManager> IEchoManagerPtr;
	class EchoManagerAdapter;
	class EchoManagerProxy;
	
	/********************* Full Definition *********************************/
	
	//--------------- Callback --------------------------//
	class ICallback {
	public:
		ICallback(){}
		virtual ~ICallback(){}
		
		virtual void onEcho(float value) = 0;
	};
	class CallbackAdapter : public ::mora::MoraAdapter<ICallback> {
	private:
		static InvokerFunctionMap	sInvokerMap;
	public:
		CallbackAdapter(::mora::Communicator* communicator, ICallbackPtr iface, const std::string& identifier);
		virtual ~CallbackAdapter();
		
		inline InvokerFunctionMap& getInvokerFunctionMap() { return sInvokerMap; }
		virtual const std::string getTypeIdentifier() const { return std::string("CALLBACK"); }
		
		static ::mora::IMoraAdapterPtr createAdapter(::mora::Communicator* communicator, ICallbackPtr iface, const std::string& identifier);
		
		static void write(ICallbackPtr value, ::mora::OutputStream& stream, ::mora::Communicator* communicator);
		static void write(const std::vector<ICallbackPtr>& value, ::mora::OutputStream& stream, ::mora::Communicator* communicator);
	};
	class CallbackProxy : public ICallback, public ::mora::IMoraProxy {
	public:
		CallbackProxy(::mora::Communicator* communicator, ::mora::RemoteObject remoteObject);
		virtual ~CallbackProxy();
		
		static std::shared_ptr<CallbackProxy> createProxy(::mora::Communicator* communicator, const std::string& qualifiedAddress);
		
		static ICallbackPtr read(::mora::InputStream& stream, ::mora::Communicator* communicator);
		static std::vector<ICallbackPtr> readList(::mora::InputStream& stream, ::mora::Communicator* communicator);
		
	public:
		void onEcho(float value);
	public:
		std::future<void> async_onEcho(float value);
	};
	
	//--------------- ExtendedCallback --------------------------//
	class IExtendedCallback {
	public:
		IExtendedCallback(){}
		virtual ~IExtendedCallback(){}
		
		virtual void extendedEcho(float value) = 0;
	};
	class ExtendedCallbackAdapter : public ::mora::MoraAdapter<IExtendedCallback> {
	private:
		static InvokerFunctionMap	sInvokerMap;
	public:
		ExtendedCallbackAdapter(::mora::Communicator* communicator, IExtendedCallbackPtr iface, const std::string& identifier);
		virtual ~ExtendedCallbackAdapter();
		
		inline InvokerFunctionMap& getInvokerFunctionMap() { return sInvokerMap; }
		virtual const std::string getTypeIdentifier() const { return std::string("EXTENDEDCALLBACK"); }
		
		static ::mora::IMoraAdapterPtr createAdapter(::mora::Communicator* communicator, IExtendedCallbackPtr iface, const std::string& identifier);
		
		static void write(IExtendedCallbackPtr value, ::mora::OutputStream& stream, ::mora::Communicator* communicator);
		static void write(const std::vector<IExtendedCallbackPtr>& value, ::mora::OutputStream& stream, ::mora::Communicator* communicator);
	};
	class ExtendedCallbackProxy : public IExtendedCallback, public ::mora::IMoraProxy {
	public:
		ExtendedCallbackProxy(::mora::Communicator* communicator, ::mora::RemoteObject remoteObject);
		virtual ~ExtendedCallbackProxy();
		
		static std::shared_ptr<ExtendedCallbackProxy> createProxy(::mora::Communicator* communicator, const std::string& qualifiedAddress);
		
		static IExtendedCallbackPtr read(::mora::InputStream& stream, ::mora::Communicator* communicator);
		static std::vector<IExtendedCallbackPtr> readList(::mora::InputStream& stream, ::mora::Communicator* communicator);
		
	public:
		void extendedEcho(float value);
	public:
		std::future<void> async_extendedEcho(float value);
	};
	
	//--------------- EchoManager --------------------------//
	class IEchoManager {
	public:
		IEchoManager(){}
		virtual ~IEchoManager(){}
		
		virtual bool echo(bool value) = 0;
		virtual ::mora::int8 echo(::mora::int8 value) = 0;
		virtual ::mora::int16 echo(::mora::int16 value) = 0;
		virtual ::mora::int32 echo(::mora::int32 value) = 0;
		virtual ::mora::int64 echo(::mora::int64 value) = 0;
		virtual float echo(float value) = 0;
		virtual double echo(double value) = 0;
		virtual std::string echo(std::string value) = 0;
		virtual MyEnum echo(MyEnum value) = 0;
		virtual SimpleStruct* echo(SimpleStruct* value) = 0;
		virtual ListStruct* echo(ListStruct* value) = 0;
		virtual std::vector<bool> echo1(std::vector<bool> value) = 0;
		virtual std::vector<::mora::int8> echo2(std::vector<::mora::int8> value) = 0;
		virtual std::vector<::mora::int16> echo3(std::vector<::mora::int16> value) = 0;
		virtual std::vector<::mora::int32> echo4(std::vector<::mora::int32> value) = 0;
		virtual std::vector<::mora::int64> echo5(std::vector<::mora::int64> value) = 0;
		virtual std::vector<float> echo6(std::vector<float> value) = 0;
		virtual std::vector<double> echo7(std::vector<double> value) = 0;
		virtual std::vector<std::string> echo8(std::vector<std::string> value) = 0;
		virtual std::vector<MyEnum> echo9(std::vector<MyEnum> value) = 0;
		virtual std::vector<SimpleStruct*> echo10(std::vector<SimpleStruct*> value) = 0;
		virtual std::vector<ListStruct*> echo11(std::vector<ListStruct*> value) = 0;
		virtual void setCallback(ICallbackPtr cb, float firstValue) = 0;
		virtual ICallbackPtr getCallback() = 0;
		virtual void throwUnknownException() = 0;
	};
	class EchoManagerAdapter : public ::mora::MoraAdapter<IEchoManager> {
	private:
		static InvokerFunctionMap	sInvokerMap;
	public:
		EchoManagerAdapter(::mora::Communicator* communicator, IEchoManagerPtr iface, const std::string& identifier);
		virtual ~EchoManagerAdapter();
		
		inline InvokerFunctionMap& getInvokerFunctionMap() { return sInvokerMap; }
		virtual const std::string getTypeIdentifier() const { return std::string("ECHOMANAGER"); }
		
		static ::mora::IMoraAdapterPtr createAdapter(::mora::Communicator* communicator, IEchoManagerPtr iface, const std::string& identifier);
		
		static void write(IEchoManagerPtr value, ::mora::OutputStream& stream, ::mora::Communicator* communicator);
		static void write(const std::vector<IEchoManagerPtr>& value, ::mora::OutputStream& stream, ::mora::Communicator* communicator);
	};
	class EchoManagerProxy : public IEchoManager, public ::mora::IMoraProxy {
	public:
		EchoManagerProxy(::mora::Communicator* communicator, ::mora::RemoteObject remoteObject);
		virtual ~EchoManagerProxy();
		
		static std::shared_ptr<EchoManagerProxy> createProxy(::mora::Communicator* communicator, const std::string& qualifiedAddress);
		
		static IEchoManagerPtr read(::mora::InputStream& stream, ::mora::Communicator* communicator);
		static std::vector<IEchoManagerPtr> readList(::mora::InputStream& stream, ::mora::Communicator* communicator);
		
	public:
		bool echo(bool value);
		::mora::int8 echo(::mora::int8 value);
		::mora::int16 echo(::mora::int16 value);
		::mora::int32 echo(::mora::int32 value);
		::mora::int64 echo(::mora::int64 value);
		float echo(float value);
		double echo(double value);
		std::string echo(std::string value);
		MyEnum echo(MyEnum value);
		SimpleStruct* echo(SimpleStruct* value);
		ListStruct* echo(ListStruct* value);
		std::vector<bool> echo1(std::vector<bool> value);
		std::vector<::mora::int8> echo2(std::vector<::mora::int8> value);
		std::vector<::mora::int16> echo3(std::vector<::mora::int16> value);
		std::vector<::mora::int32> echo4(std::vector<::mora::int32> value);
		std::vector<::mora::int64> echo5(std::vector<::mora::int64> value);
		std::vector<float> echo6(std::vector<float> value);
		std::vector<double> echo7(std::vector<double> value);
		std::vector<std::string> echo8(std::vector<std::string> value);
		std::vector<MyEnum> echo9(std::vector<MyEnum> value);
		std::vector<SimpleStruct*> echo10(std::vector<SimpleStruct*> value);
		std::vector<ListStruct*> echo11(std::vector<ListStruct*> value);
		void setCallback(ICallbackPtr cb, float firstValue);
		ICallbackPtr getCallback();
		void throwUnknownException();
	public:
		std::future<bool> async_echo(bool value);
		std::future<::mora::int8> async_echo(::mora::int8 value);
		std::future<::mora::int16> async_echo(::mora::int16 value);
		std::future<::mora::int32> async_echo(::mora::int32 value);
		std::future<::mora::int64> async_echo(::mora::int64 value);
		std::future<float> async_echo(float value);
		std::future<double> async_echo(double value);
		std::future<std::string> async_echo(std::string value);
		std::future<MyEnum> async_echo(MyEnum value);
		std::future<SimpleStruct*> async_echo(SimpleStruct* value);
		std::future<ListStruct*> async_echo(ListStruct* value);
		std::future<std::vector<bool>> async_echo1(std::vector<bool> value);
		std::future<std::vector<::mora::int8>> async_echo2(std::vector<::mora::int8> value);
		std::future<std::vector<::mora::int16>> async_echo3(std::vector<::mora::int16> value);
		std::future<std::vector<::mora::int32>> async_echo4(std::vector<::mora::int32> value);
		std::future<std::vector<::mora::int64>> async_echo5(std::vector<::mora::int64> value);
		std::future<std::vector<float>> async_echo6(std::vector<float> value);
		std::future<std::vector<double>> async_echo7(std::vector<double> value);
		std::future<std::vector<std::string>> async_echo8(std::vector<std::string> value);
		std::future<std::vector<MyEnum>> async_echo9(std::vector<MyEnum> value);
		std::future<std::vector<SimpleStruct*>> async_echo10(std::vector<SimpleStruct*> value);
		std::future<std::vector<ListStruct*>> async_echo11(std::vector<ListStruct*> value);
		std::future<void> async_setCallback(ICallbackPtr cb, float firstValue);
		std::future<ICallbackPtr> async_getCallback();
		std::future<void> async_throwUnknownException();
	};
	
	

} /*de*/ } /*sos*/ } /*mora*/ } /*examples*/ 
#endif //