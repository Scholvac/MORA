#include<Communicator.h>
#include<MoraPreReq.h>
#include<RemoteMethodCall.h>
#include<SerializeTypes.h>
#include<Streams.h>
#include<future>
#include<loguru.hpp>
#include<vector>


namespace de{ namespace sos{ namespace mora{ namespace examples{ 

////////////////////////////////////////////////////////////////////////////////
//							Enumerations									  //
////////////////////////////////////////////////////////////////////////////////
/*******************	Enumeration: MyEnum    ********************/
::mora::int32 MyEnumUtil::valueOf(const MyEnum value) {
	switch(value) {
	case KEY: return 0;
	case VALUE: return 1;
	}	
	throw "Invalid value";
}
MyEnum MyEnumUtil::valueOf(const ::mora::int32 value){
	switch(value){
	case 0: return KEY;
	case 1: return VALUE;
	}
	throw "Invalid value";
}

MyEnum MyEnumUtil::read(::mora::InputStream& stream){
	::mora::int32 val;
	stream >> val;
	return valueOf(val);
}
std::vector<MyEnum> MyEnumUtil::readList(::mora::InputStream& stream){
	::mora::int32 c;
	stream >> c;
	std::vector<MyEnum> out(c);
	for (int i = 0; i < c; i++)
		out[i] = read(stream);
	return out;
}

void MyEnumUtil::write(const MyEnum value, ::mora::OutputStream& stream){
	stream << valueOf(value);
}
void MyEnumUtil::write(const std::vector<MyEnum>& value, ::mora::OutputStream& stream){
	::mora::int32 size = (::mora::int32)value.size();
	stream << size;
	for (int i = 0; i < size; i++)
		stream << valueOf(value[i]);
}

////////////////////////////////////////////////////////////////////////////////
//								Structs										  //
////////////////////////////////////////////////////////////////////////////////
			
/********************* struct SimpleStruct *********************************/
SimpleStruct::SimpleStruct()
	:	
	boolValue(false), 
	byteValue((::mora::int8)0), 
	shortValue((::mora::int16)0), 
	intValue(0), 
	longValue(0), 
	floatValue(0.0f), 
	doubleValue(0.0), 
	stringValue(""), 
	enumValue(KEY), 
	structValue(NULL)
{
}

SimpleStruct::SimpleStruct(const SimpleStruct& copy)
	:	
	boolValue(copy.boolValue), 
	byteValue(copy.byteValue), 
	shortValue(copy.shortValue), 
	intValue(copy.intValue), 
	longValue(copy.longValue), 
	floatValue(copy.floatValue), 
	doubleValue(copy.doubleValue), 
	stringValue(copy.stringValue), 
	enumValue(copy.enumValue), 
	structValue(copy.structValue)
{
}
SimpleStruct::~SimpleStruct()
{
	if (structValue != NULL)
		delete structValue;
}



SimpleStruct* SimpleStruct::read(::mora::InputStream& stream) {
	::mora::int8 flag;
	stream >> flag;
	if (flag == ::mora::STRUCT_NULL)
		return NULL;
	CHECK_EQ_F(flag, ::mora::STRUCT_START, "Expecting %i (start flag) but found: %i", ::mora::STRUCT_START, flag);
	
	SimpleStruct* out = new SimpleStruct();
	stream >> out->boolValue;
	stream >> out->byteValue;
	stream >> out->shortValue;
	stream >> out->intValue;
	stream >> out->longValue;
	stream >> out->floatValue;
	stream >> out->doubleValue;
	stream >> out->stringValue;
	out->enumValue = MyEnumUtil::read(stream);
	out->structValue = SimpleStruct::read(stream);
	
	stream >> flag;
	CHECK_EQ_F(flag, ::mora::STRUCT_END, "Expecting %i (end flag) but found: %i", ::mora::STRUCT_END, flag);
	return out;
}
std::vector<SimpleStruct*> SimpleStruct::readList(::mora::InputStream& stream){
	::mora::int32 c;
	stream >> c;
	std::vector<SimpleStruct*> out(c);
	for (int i = 0; i < c; i++)
		out[i] = read(stream);
	return out;
}

void SimpleStruct::write(const SimpleStruct* value, ::mora::OutputStream& stream) {
	if (value == NULL){
		stream << ::mora::STRUCT_NULL;
		return ;
	}
	
	stream << ::mora::STRUCT_START;
	stream << value->boolValue;
	stream << value->byteValue;
	stream << value->shortValue;
	stream << value->intValue;
	stream << value->longValue;
	stream << value->floatValue;
	stream << value->doubleValue;
	stream << value->stringValue;
	MyEnumUtil::write(value->enumValue, stream);
	SimpleStruct::write(value->structValue, stream);
	stream << ::mora::STRUCT_END;
}
void SimpleStruct::write(const std::vector<SimpleStruct*>& value, ::mora::OutputStream& stream) {
	::mora::int32 size = (::mora::int32)value.size();
	stream << size;
	for (int i = 0; i < size; i++)
		write(value[i], stream);
}
/********************* struct ListStruct *********************************/
ListStruct::ListStruct()
	:	
	boolListValue(std::vector<bool>()), 
	byteListValue(std::vector<::mora::int8>()), 
	shortListValue(std::vector<::mora::int16>()), 
	intListValue(std::vector<::mora::int32>()), 
	longListValue(std::vector<::mora::int64>()), 
	floatListValue(std::vector<float>()), 
	doubleListValue(std::vector<double>()), 
	stringListValue(std::vector<std::string>()), 
	enumListValue(std::vector<MyEnum>()), 
	structListValue(std::vector<SimpleStruct*>())
{
}

ListStruct::ListStruct(const ListStruct& copy)
	:	
	boolListValue(copy.boolListValue.begin(), copy.boolListValue.end()), 
	byteListValue(copy.byteListValue.begin(), copy.byteListValue.end()), 
	shortListValue(copy.shortListValue.begin(), copy.shortListValue.end()), 
	intListValue(copy.intListValue.begin(), copy.intListValue.end()), 
	longListValue(copy.longListValue.begin(), copy.longListValue.end()), 
	floatListValue(copy.floatListValue.begin(), copy.floatListValue.end()), 
	doubleListValue(copy.doubleListValue.begin(), copy.doubleListValue.end()), 
	stringListValue(copy.stringListValue.begin(), copy.stringListValue.end()), 
	enumListValue(copy.enumListValue.begin(), copy.enumListValue.end()), 
	structListValue(copy.structListValue.begin(), copy.structListValue.end())
{
}
ListStruct::~ListStruct()
{
	for (size_t i = 0; i < structListValue.size(); i++)
		if (structListValue[i] != NULL)
			delete structListValue[i];
}



ListStruct* ListStruct::read(::mora::InputStream& stream) {
	::mora::int8 flag;
	stream >> flag;
	if (flag == ::mora::STRUCT_NULL)
		return NULL;
	CHECK_EQ_F(flag, ::mora::STRUCT_START, "Expecting %i (start flag) but found: %i", ::mora::STRUCT_START, flag);
	
	ListStruct* out = new ListStruct();
	stream >> out->boolListValue;
	stream >> out->byteListValue;
	stream >> out->shortListValue;
	stream >> out->intListValue;
	stream >> out->longListValue;
	stream >> out->floatListValue;
	stream >> out->doubleListValue;
	stream >> out->stringListValue;
	out->enumListValue = MyEnumUtil::readList(stream);
	out->structListValue = SimpleStruct::readList(stream);
	
	stream >> flag;
	CHECK_EQ_F(flag, ::mora::STRUCT_END, "Expecting %i (end flag) but found: %i", ::mora::STRUCT_END, flag);
	return out;
}
std::vector<ListStruct*> ListStruct::readList(::mora::InputStream& stream){
	::mora::int32 c;
	stream >> c;
	std::vector<ListStruct*> out(c);
	for (int i = 0; i < c; i++)
		out[i] = read(stream);
	return out;
}

void ListStruct::write(const ListStruct* value, ::mora::OutputStream& stream) {
	if (value == NULL){
		stream << ::mora::STRUCT_NULL;
		return ;
	}
	
	stream << ::mora::STRUCT_START;
	stream << value->boolListValue;
	stream << value->byteListValue;
	stream << value->shortListValue;
	stream << value->intListValue;
	stream << value->longListValue;
	stream << value->floatListValue;
	stream << value->doubleListValue;
	stream << value->stringListValue;
	MyEnumUtil::write(value->enumListValue, stream);
	SimpleStruct::write(value->structListValue, stream);
	stream << ::mora::STRUCT_END;
}
void ListStruct::write(const std::vector<ListStruct*>& value, ::mora::OutputStream& stream) {
	::mora::int32 size = (::mora::int32)value.size();
	stream << size;
	for (int i = 0; i < size; i++)
		write(value[i], stream);
}



////////////////////////////////////////////////////////////////////////////////
//							Interfaces										  //
////////////////////////////////////////////////////////////////////////////////
			
/********************* interface Callback *********************************/


	// -------------------------- Adapter -------------------------------//
	static void _invoke_Callback__getType_(ICallback* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		static std::string tn("CALLBACK");
		os << tn;
	}
	static void _invoke_Callback_OnEcho_FLOAT_(ICallback* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		float _value;
		is >> _value;
		
		delegate->onEcho(_value);
		
	}
	
	CallbackAdapter::InvokerFunctionMap createCallbackInvokerMap()
	{
		CallbackAdapter::InvokerFunctionMap im;
		im.insert(std::make_pair("_getType_", &_invoke_Callback__getType_));
		im.insert(std::make_pair("OnEcho_FLOAT_", &_invoke_Callback_OnEcho_FLOAT_));
		return im;
	}
	
	CallbackAdapter::InvokerFunctionMap CallbackAdapter::sInvokerMap = createCallbackInvokerMap();
	
	CallbackAdapter::CallbackAdapter(::mora::Communicator* communicator, ICallbackPtr iface, const std::string& identifier)
		:	::mora::MoraAdapter<ICallback>(communicator, iface, identifier)
	{
	}
	
	CallbackAdapter::~CallbackAdapter()
	{
	}
	
	
	::mora::IMoraAdapterPtr CallbackAdapter::createAdapter(::mora::Communicator* communicator, ICallbackPtr iface, const std::string& identifier)
	{
		return communicator->registerAdapter(::mora::IMoraAdapterPtr(new CallbackAdapter(communicator, iface, identifier)));
	}
	
	void CallbackAdapter::write(ICallbackPtr value, ::mora::OutputStream& stream, ::mora::Communicator* communicator){
		if (!value){
			stream << ::mora::STRUCT_NULL;
			return ;
		}
		stream << ::mora::STRUCT_START;
		::mora::IMoraAdapterPtr adapter = communicator->getAdapter(value);
		if (!adapter){
			adapter = createAdapter(communicator, value, communicator->createRandomIdentifier());
		}
		CHECK_F(adapter != nullptr, "Failed to create Adapter");
		stream << adapter->getQualifiedIdentifier();
	}
	void CallbackAdapter::write(const std::vector<ICallbackPtr>& value, ::mora::OutputStream& stream, ::mora::Communicator* communicator){
		::mora::int32 size = (::mora::int32)value.size();
		stream << size;
		for (int i = 0; i < size; i++)
			write(value[i], stream, communicator);
	}
	
	
	// -------------------------- Proxy -------------------------------//
	CallbackProxy::CallbackProxy(::mora::Communicator* communicator, ::mora::RemoteObject remoteObject)
		:	::mora::IMoraProxy(communicator, remoteObject)
	{
	}
	CallbackProxy::~CallbackProxy(){
	}
	
	std::shared_ptr<CallbackProxy> CallbackProxy::createProxy(::mora::Communicator* communicator, const std::string& qualifiedAddress){
		CallbackProxy* proxy = new CallbackProxy(communicator, ::mora::RemoteObject::create(qualifiedAddress));
		static std::string tn("CALLBACK");
		if (proxy->checkType(tn)){
			std::shared_ptr<CallbackProxy> ptr(proxy);
			return ptr;
		}else{
			delete proxy;
			return std::shared_ptr<CallbackProxy>(NULL);
		}
	}
	ICallbackPtr CallbackProxy::read(::mora::InputStream& stream, ::mora::Communicator* communicator) {
		::mora::int8 flag;
		stream >> flag;
		if (flag == ::mora::STRUCT_NULL)
			return ICallbackPtr(NULL);
		CHECK_EQ_F(flag, ::mora::STRUCT_START, "Expected %i (start flag for Callback but found: %i", ::mora::STRUCT_START, flag);
		std::string quid;
		stream >> quid;
		::mora::IMoraProxyPtr ptr = communicator->getProxy(quid);
		ICallbackPtr proxy;
		if (ptr) {
			proxy = std::dynamic_pointer_cast<ICallback>(ptr);
		}
		if (proxy == nullptr){
			proxy = createProxy(communicator, quid);
		}
		CHECK_F(proxy != nullptr, "Failed to create proxy of: %s", quid.c_str());
		return proxy;
	}
	std::vector<ICallbackPtr> CallbackProxy::readList(::mora::InputStream& stream, ::mora::Communicator* communicator) {
		::mora::int32 size;
		stream >> size;
		std::vector<ICallbackPtr> out(size);
		for (int i = 0; i < size; i++)
			out.push_back(read(stream, communicator));
		return out;
	}
	
	
	//----------	Interface implementation 	---------------------//
	
	void CallbackProxy::onEcho(float value){
		std::future<void> future = async_onEcho(value);
		future.get();
	}
	
	
	class OnEcho_FLOAT__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		float	_value;
		std::promise<void>	promise;
	public:
		OnEcho_FLOAT__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, float value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~OnEcho_FLOAT__RemoteCall()
		{
		}
		
		std::future<void> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			promise.set_value();
		}
	};
	std::future<void> CallbackProxy::async_onEcho(float value){
		OnEcho_FLOAT__RemoteCall* call = new OnEcho_FLOAT__RemoteCall(getCommunicator(), getMethod("OnEcho_FLOAT_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	
/********************* interface ExtendedCallback *********************************/


	// -------------------------- Adapter -------------------------------//
	static void _invoke_ExtendedCallback__getType_(IExtendedCallback* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		static std::string tn("EXTENDEDCALLBACK");
		os << tn;
	}
	static void _invoke_ExtendedCallback_ExtendedEcho_FLOAT_(IExtendedCallback* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		float _value;
		is >> _value;
		
		delegate->extendedEcho(_value);
		
	}
	
	ExtendedCallbackAdapter::InvokerFunctionMap createExtendedCallbackInvokerMap()
	{
		ExtendedCallbackAdapter::InvokerFunctionMap im;
		im.insert(std::make_pair("_getType_", &_invoke_ExtendedCallback__getType_));
		im.insert(std::make_pair("ExtendedEcho_FLOAT_", &_invoke_ExtendedCallback_ExtendedEcho_FLOAT_));
		return im;
	}
	
	ExtendedCallbackAdapter::InvokerFunctionMap ExtendedCallbackAdapter::sInvokerMap = createExtendedCallbackInvokerMap();
	
	ExtendedCallbackAdapter::ExtendedCallbackAdapter(::mora::Communicator* communicator, IExtendedCallbackPtr iface, const std::string& identifier)
		:	::mora::MoraAdapter<IExtendedCallback>(communicator, iface, identifier)
	{
	}
	
	ExtendedCallbackAdapter::~ExtendedCallbackAdapter()
	{
	}
	
	
	::mora::IMoraAdapterPtr ExtendedCallbackAdapter::createAdapter(::mora::Communicator* communicator, IExtendedCallbackPtr iface, const std::string& identifier)
	{
		return communicator->registerAdapter(::mora::IMoraAdapterPtr(new ExtendedCallbackAdapter(communicator, iface, identifier)));
	}
	
	void ExtendedCallbackAdapter::write(IExtendedCallbackPtr value, ::mora::OutputStream& stream, ::mora::Communicator* communicator){
		if (!value){
			stream << ::mora::STRUCT_NULL;
			return ;
		}
		stream << ::mora::STRUCT_START;
		::mora::IMoraAdapterPtr adapter = communicator->getAdapter(value);
		if (!adapter){
			adapter = createAdapter(communicator, value, communicator->createRandomIdentifier());
		}
		CHECK_F(adapter != nullptr, "Failed to create Adapter");
		stream << adapter->getQualifiedIdentifier();
	}
	void ExtendedCallbackAdapter::write(const std::vector<IExtendedCallbackPtr>& value, ::mora::OutputStream& stream, ::mora::Communicator* communicator){
		::mora::int32 size = (::mora::int32)value.size();
		stream << size;
		for (int i = 0; i < size; i++)
			write(value[i], stream, communicator);
	}
	
	
	// -------------------------- Proxy -------------------------------//
	ExtendedCallbackProxy::ExtendedCallbackProxy(::mora::Communicator* communicator, ::mora::RemoteObject remoteObject)
		:	::mora::IMoraProxy(communicator, remoteObject)
	{
	}
	ExtendedCallbackProxy::~ExtendedCallbackProxy(){
	}
	
	std::shared_ptr<ExtendedCallbackProxy> ExtendedCallbackProxy::createProxy(::mora::Communicator* communicator, const std::string& qualifiedAddress){
		ExtendedCallbackProxy* proxy = new ExtendedCallbackProxy(communicator, ::mora::RemoteObject::create(qualifiedAddress));
		static std::string tn("EXTENDEDCALLBACK");
		if (proxy->checkType(tn)){
			std::shared_ptr<ExtendedCallbackProxy> ptr(proxy);
			return ptr;
		}else{
			delete proxy;
			return std::shared_ptr<ExtendedCallbackProxy>(NULL);
		}
	}
	IExtendedCallbackPtr ExtendedCallbackProxy::read(::mora::InputStream& stream, ::mora::Communicator* communicator) {
		::mora::int8 flag;
		stream >> flag;
		if (flag == ::mora::STRUCT_NULL)
			return IExtendedCallbackPtr(NULL);
		CHECK_EQ_F(flag, ::mora::STRUCT_START, "Expected %i (start flag for ExtendedCallback but found: %i", ::mora::STRUCT_START, flag);
		std::string quid;
		stream >> quid;
		::mora::IMoraProxyPtr ptr = communicator->getProxy(quid);
		IExtendedCallbackPtr proxy;
		if (ptr) {
			proxy = std::dynamic_pointer_cast<IExtendedCallback>(ptr);
		}
		if (proxy == nullptr){
			proxy = createProxy(communicator, quid);
		}
		CHECK_F(proxy != nullptr, "Failed to create proxy of: %s", quid.c_str());
		return proxy;
	}
	std::vector<IExtendedCallbackPtr> ExtendedCallbackProxy::readList(::mora::InputStream& stream, ::mora::Communicator* communicator) {
		::mora::int32 size;
		stream >> size;
		std::vector<IExtendedCallbackPtr> out(size);
		for (int i = 0; i < size; i++)
			out.push_back(read(stream, communicator));
		return out;
	}
	
	
	//----------	Interface implementation 	---------------------//
	
	void ExtendedCallbackProxy::extendedEcho(float value){
		std::future<void> future = async_extendedEcho(value);
		future.get();
	}
	
	
	class ExtendedEcho_FLOAT__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		float	_value;
		std::promise<void>	promise;
	public:
		ExtendedEcho_FLOAT__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, float value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~ExtendedEcho_FLOAT__RemoteCall()
		{
		}
		
		std::future<void> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			promise.set_value();
		}
	};
	std::future<void> ExtendedCallbackProxy::async_extendedEcho(float value){
		ExtendedEcho_FLOAT__RemoteCall* call = new ExtendedEcho_FLOAT__RemoteCall(getCommunicator(), getMethod("ExtendedEcho_FLOAT_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	
/********************* interface EchoManager *********************************/


	// -------------------------- Adapter -------------------------------//
	static void _invoke_EchoManager__getType_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		static std::string tn("ECHOMANAGER");
		os << tn;
	}
	static void _invoke_EchoManager_Echo_BOOL_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		bool _value;
		is >> _value;
		
		bool _result = delegate->echo(_value);
		
		os << _result;
		
	}
	static void _invoke_EchoManager_Echo_BYTE_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		::mora::int8 _value;
		is >> _value;
		
		::mora::int8 _result = delegate->echo(_value);
		
		os << _result;
		
	}
	static void _invoke_EchoManager_Echo_SHORT_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		::mora::int16 _value;
		is >> _value;
		
		::mora::int16 _result = delegate->echo(_value);
		
		os << _result;
		
	}
	static void _invoke_EchoManager_Echo_INTEGER_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		::mora::int32 _value;
		is >> _value;
		
		::mora::int32 _result = delegate->echo(_value);
		
		os << _result;
		
	}
	static void _invoke_EchoManager_Echo_LONG_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		::mora::int64 _value;
		is >> _value;
		
		::mora::int64 _result = delegate->echo(_value);
		
		os << _result;
		
	}
	static void _invoke_EchoManager_Echo_FLOAT_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		float _value;
		is >> _value;
		
		float _result = delegate->echo(_value);
		
		os << _result;
		
	}
	static void _invoke_EchoManager_Echo_DOUBLE_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		double _value;
		is >> _value;
		
		double _result = delegate->echo(_value);
		
		os << _result;
		
	}
	static void _invoke_EchoManager_Echo_STRING_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		std::string _value;
		is >> _value;
		
		std::string _result = delegate->echo(_value);
		
		os << _result;
		
	}
	static void _invoke_EchoManager_Echo_MyEnum_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		MyEnum _value = MyEnumUtil::read(is);
		
		MyEnum _result = delegate->echo(_value);
		
		MyEnumUtil::write(_result, os);
		
	}
	static void _invoke_EchoManager_Echo_SimpleStruct_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		SimpleStruct* _value = SimpleStruct::read(is);
		
		SimpleStruct* _result = delegate->echo(_value);
		
		SimpleStruct::write(_result, os);
		
		if (_value != NULL)
			delete _value;
	}
	static void _invoke_EchoManager_Echo_ListStruct_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		ListStruct* _value = ListStruct::read(is);
		
		ListStruct* _result = delegate->echo(_value);
		
		ListStruct::write(_result, os);
		
		if (_value != NULL)
			delete _value;
	}
	static void _invoke_EchoManager_Echo1_BoolList_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		std::vector<bool> _value;
		is >> _value;
		
		std::vector<bool> _result = delegate->echo1(_value);
		
		os << _result;
		
	}
	static void _invoke_EchoManager_Echo2_ByteList_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		std::vector<::mora::int8> _value;
		is >> _value;
		
		std::vector<::mora::int8> _result = delegate->echo2(_value);
		
		os << _result;
		
	}
	static void _invoke_EchoManager_Echo3_ShortList_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		std::vector<::mora::int16> _value;
		is >> _value;
		
		std::vector<::mora::int16> _result = delegate->echo3(_value);
		
		os << _result;
		
	}
	static void _invoke_EchoManager_Echo4_IntList_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		std::vector<::mora::int32> _value;
		is >> _value;
		
		std::vector<::mora::int32> _result = delegate->echo4(_value);
		
		os << _result;
		
	}
	static void _invoke_EchoManager_Echo5_LongList_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		std::vector<::mora::int64> _value;
		is >> _value;
		
		std::vector<::mora::int64> _result = delegate->echo5(_value);
		
		os << _result;
		
	}
	static void _invoke_EchoManager_Echo6_FloatList_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		std::vector<float> _value;
		is >> _value;
		
		std::vector<float> _result = delegate->echo6(_value);
		
		os << _result;
		
	}
	static void _invoke_EchoManager_Echo7_DoubleList_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		std::vector<double> _value;
		is >> _value;
		
		std::vector<double> _result = delegate->echo7(_value);
		
		os << _result;
		
	}
	static void _invoke_EchoManager_Echo8_StringList_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		std::vector<std::string> _value;
		is >> _value;
		
		std::vector<std::string> _result = delegate->echo8(_value);
		
		os << _result;
		
	}
	static void _invoke_EchoManager_Echo9_MyEnumList_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		std::vector<MyEnum> _value = MyEnumUtil::readList(is);
		
		std::vector<MyEnum> _result = delegate->echo9(_value);
		
		MyEnumUtil::write(_result, os);
		
	}
	static void _invoke_EchoManager_Echo10_SimpleStructList_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		std::vector<SimpleStruct*> _value = SimpleStruct::readList(is);
		
		std::vector<SimpleStruct*> _result = delegate->echo10(_value);
		
		SimpleStruct::write(_result, os);
		
		for (size_t _i_ = 0; _i_ < _value.size(); ++_i_)
			if (_value[_i_] != NULL)
				delete _value[_i_];
	}
	static void _invoke_EchoManager_Echo11_ListListStruct_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		std::vector<ListStruct*> _value = ListStruct::readList(is);
		
		std::vector<ListStruct*> _result = delegate->echo11(_value);
		
		ListStruct::write(_result, os);
		
		for (size_t _i_ = 0; _i_ < _value.size(); ++_i_)
			if (_value[_i_] != NULL)
				delete _value[_i_];
	}
	static void _invoke_EchoManager_SetCallback_Callback_FLOAT_(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		ICallbackPtr _cb = CallbackProxy::read(is, communicator);
		float _firstValue;
		is >> _firstValue;
		
		delegate->setCallback(_cb, _firstValue);
		
	}
	static void _invoke_EchoManager_GetCallback(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		
		ICallbackPtr _result = delegate->getCallback();
		
		CallbackAdapter::write(_result, os, communicator);
		
	}
	static void _invoke_EchoManager_ThrowUnknownException(IEchoManager* delegate, ::mora::InputStream& is, ::mora::OutputStream& os, ::mora::Communicator* communicator){
		
		delegate->throwUnknownException();
		
	}
	
	EchoManagerAdapter::InvokerFunctionMap createEchoManagerInvokerMap()
	{
		EchoManagerAdapter::InvokerFunctionMap im;
		im.insert(std::make_pair("_getType_", &_invoke_EchoManager__getType_));
		im.insert(std::make_pair("Echo_BOOL_", &_invoke_EchoManager_Echo_BOOL_));
		im.insert(std::make_pair("Echo_BYTE_", &_invoke_EchoManager_Echo_BYTE_));
		im.insert(std::make_pair("Echo_SHORT_", &_invoke_EchoManager_Echo_SHORT_));
		im.insert(std::make_pair("Echo_INTEGER_", &_invoke_EchoManager_Echo_INTEGER_));
		im.insert(std::make_pair("Echo_LONG_", &_invoke_EchoManager_Echo_LONG_));
		im.insert(std::make_pair("Echo_FLOAT_", &_invoke_EchoManager_Echo_FLOAT_));
		im.insert(std::make_pair("Echo_DOUBLE_", &_invoke_EchoManager_Echo_DOUBLE_));
		im.insert(std::make_pair("Echo_STRING_", &_invoke_EchoManager_Echo_STRING_));
		im.insert(std::make_pair("Echo_MyEnum_", &_invoke_EchoManager_Echo_MyEnum_));
		im.insert(std::make_pair("Echo_SimpleStruct_", &_invoke_EchoManager_Echo_SimpleStruct_));
		im.insert(std::make_pair("Echo_ListStruct_", &_invoke_EchoManager_Echo_ListStruct_));
		im.insert(std::make_pair("Echo1_BoolList_", &_invoke_EchoManager_Echo1_BoolList_));
		im.insert(std::make_pair("Echo2_ByteList_", &_invoke_EchoManager_Echo2_ByteList_));
		im.insert(std::make_pair("Echo3_ShortList_", &_invoke_EchoManager_Echo3_ShortList_));
		im.insert(std::make_pair("Echo4_IntList_", &_invoke_EchoManager_Echo4_IntList_));
		im.insert(std::make_pair("Echo5_LongList_", &_invoke_EchoManager_Echo5_LongList_));
		im.insert(std::make_pair("Echo6_FloatList_", &_invoke_EchoManager_Echo6_FloatList_));
		im.insert(std::make_pair("Echo7_DoubleList_", &_invoke_EchoManager_Echo7_DoubleList_));
		im.insert(std::make_pair("Echo8_StringList_", &_invoke_EchoManager_Echo8_StringList_));
		im.insert(std::make_pair("Echo9_MyEnumList_", &_invoke_EchoManager_Echo9_MyEnumList_));
		im.insert(std::make_pair("Echo10_SimpleStructList_", &_invoke_EchoManager_Echo10_SimpleStructList_));
		im.insert(std::make_pair("Echo11_ListListStruct_", &_invoke_EchoManager_Echo11_ListListStruct_));
		im.insert(std::make_pair("SetCallback_Callback_FLOAT_", &_invoke_EchoManager_SetCallback_Callback_FLOAT_));
		im.insert(std::make_pair("GetCallback", &_invoke_EchoManager_GetCallback));
		im.insert(std::make_pair("ThrowUnknownException", &_invoke_EchoManager_ThrowUnknownException));
		return im;
	}
	
	EchoManagerAdapter::InvokerFunctionMap EchoManagerAdapter::sInvokerMap = createEchoManagerInvokerMap();
	
	EchoManagerAdapter::EchoManagerAdapter(::mora::Communicator* communicator, IEchoManagerPtr iface, const std::string& identifier)
		:	::mora::MoraAdapter<IEchoManager>(communicator, iface, identifier)
	{
	}
	
	EchoManagerAdapter::~EchoManagerAdapter()
	{
	}
	
	
	::mora::IMoraAdapterPtr EchoManagerAdapter::createAdapter(::mora::Communicator* communicator, IEchoManagerPtr iface, const std::string& identifier)
	{
		return communicator->registerAdapter(::mora::IMoraAdapterPtr(new EchoManagerAdapter(communicator, iface, identifier)));
	}
	
	void EchoManagerAdapter::write(IEchoManagerPtr value, ::mora::OutputStream& stream, ::mora::Communicator* communicator){
		if (!value){
			stream << ::mora::STRUCT_NULL;
			return ;
		}
		stream << ::mora::STRUCT_START;
		::mora::IMoraAdapterPtr adapter = communicator->getAdapter(value);
		if (!adapter){
			adapter = createAdapter(communicator, value, communicator->createRandomIdentifier());
		}
		CHECK_F(adapter != nullptr, "Failed to create Adapter");
		stream << adapter->getQualifiedIdentifier();
	}
	void EchoManagerAdapter::write(const std::vector<IEchoManagerPtr>& value, ::mora::OutputStream& stream, ::mora::Communicator* communicator){
		::mora::int32 size = (::mora::int32)value.size();
		stream << size;
		for (int i = 0; i < size; i++)
			write(value[i], stream, communicator);
	}
	
	
	// -------------------------- Proxy -------------------------------//
	EchoManagerProxy::EchoManagerProxy(::mora::Communicator* communicator, ::mora::RemoteObject remoteObject)
		:	::mora::IMoraProxy(communicator, remoteObject)
	{
	}
	EchoManagerProxy::~EchoManagerProxy(){
	}
	
	std::shared_ptr<EchoManagerProxy> EchoManagerProxy::createProxy(::mora::Communicator* communicator, const std::string& qualifiedAddress){
		EchoManagerProxy* proxy = new EchoManagerProxy(communicator, ::mora::RemoteObject::create(qualifiedAddress));
		static std::string tn("ECHOMANAGER");
		if (proxy->checkType(tn)){
			std::shared_ptr<EchoManagerProxy> ptr(proxy);
			return ptr;
		}else{
			delete proxy;
			return std::shared_ptr<EchoManagerProxy>(NULL);
		}
	}
	IEchoManagerPtr EchoManagerProxy::read(::mora::InputStream& stream, ::mora::Communicator* communicator) {
		::mora::int8 flag;
		stream >> flag;
		if (flag == ::mora::STRUCT_NULL)
			return IEchoManagerPtr(NULL);
		CHECK_EQ_F(flag, ::mora::STRUCT_START, "Expected %i (start flag for EchoManager but found: %i", ::mora::STRUCT_START, flag);
		std::string quid;
		stream >> quid;
		::mora::IMoraProxyPtr ptr = communicator->getProxy(quid);
		IEchoManagerPtr proxy;
		if (ptr) {
			proxy = std::dynamic_pointer_cast<IEchoManager>(ptr);
		}
		if (proxy == nullptr){
			proxy = createProxy(communicator, quid);
		}
		CHECK_F(proxy != nullptr, "Failed to create proxy of: %s", quid.c_str());
		return proxy;
	}
	std::vector<IEchoManagerPtr> EchoManagerProxy::readList(::mora::InputStream& stream, ::mora::Communicator* communicator) {
		::mora::int32 size;
		stream >> size;
		std::vector<IEchoManagerPtr> out(size);
		for (int i = 0; i < size; i++)
			out.push_back(read(stream, communicator));
		return out;
	}
	
	
	//----------	Interface implementation 	---------------------//
	
	bool EchoManagerProxy::echo(bool value){
		std::future<bool> future = async_echo(value);
		return future.get();
	}
	::mora::int8 EchoManagerProxy::echo(::mora::int8 value){
		std::future<::mora::int8> future = async_echo(value);
		return future.get();
	}
	::mora::int16 EchoManagerProxy::echo(::mora::int16 value){
		std::future<::mora::int16> future = async_echo(value);
		return future.get();
	}
	::mora::int32 EchoManagerProxy::echo(::mora::int32 value){
		std::future<::mora::int32> future = async_echo(value);
		return future.get();
	}
	::mora::int64 EchoManagerProxy::echo(::mora::int64 value){
		std::future<::mora::int64> future = async_echo(value);
		return future.get();
	}
	float EchoManagerProxy::echo(float value){
		std::future<float> future = async_echo(value);
		return future.get();
	}
	double EchoManagerProxy::echo(double value){
		std::future<double> future = async_echo(value);
		return future.get();
	}
	std::string EchoManagerProxy::echo(std::string value){
		std::future<std::string> future = async_echo(value);
		return future.get();
	}
	MyEnum EchoManagerProxy::echo(MyEnum value){
		std::future<MyEnum> future = async_echo(value);
		return future.get();
	}
	SimpleStruct* EchoManagerProxy::echo(SimpleStruct* value){
		std::future<SimpleStruct*> future = async_echo(value);
		return future.get();
	}
	ListStruct* EchoManagerProxy::echo(ListStruct* value){
		std::future<ListStruct*> future = async_echo(value);
		return future.get();
	}
	std::vector<bool> EchoManagerProxy::echo1(std::vector<bool> value){
		std::future<std::vector<bool>> future = async_echo1(value);
		return future.get();
	}
	std::vector<::mora::int8> EchoManagerProxy::echo2(std::vector<::mora::int8> value){
		std::future<std::vector<::mora::int8>> future = async_echo2(value);
		return future.get();
	}
	std::vector<::mora::int16> EchoManagerProxy::echo3(std::vector<::mora::int16> value){
		std::future<std::vector<::mora::int16>> future = async_echo3(value);
		return future.get();
	}
	std::vector<::mora::int32> EchoManagerProxy::echo4(std::vector<::mora::int32> value){
		std::future<std::vector<::mora::int32>> future = async_echo4(value);
		return future.get();
	}
	std::vector<::mora::int64> EchoManagerProxy::echo5(std::vector<::mora::int64> value){
		std::future<std::vector<::mora::int64>> future = async_echo5(value);
		return future.get();
	}
	std::vector<float> EchoManagerProxy::echo6(std::vector<float> value){
		std::future<std::vector<float>> future = async_echo6(value);
		return future.get();
	}
	std::vector<double> EchoManagerProxy::echo7(std::vector<double> value){
		std::future<std::vector<double>> future = async_echo7(value);
		return future.get();
	}
	std::vector<std::string> EchoManagerProxy::echo8(std::vector<std::string> value){
		std::future<std::vector<std::string>> future = async_echo8(value);
		return future.get();
	}
	std::vector<MyEnum> EchoManagerProxy::echo9(std::vector<MyEnum> value){
		std::future<std::vector<MyEnum>> future = async_echo9(value);
		return future.get();
	}
	std::vector<SimpleStruct*> EchoManagerProxy::echo10(std::vector<SimpleStruct*> value){
		std::future<std::vector<SimpleStruct*>> future = async_echo10(value);
		return future.get();
	}
	std::vector<ListStruct*> EchoManagerProxy::echo11(std::vector<ListStruct*> value){
		std::future<std::vector<ListStruct*>> future = async_echo11(value);
		return future.get();
	}
	void EchoManagerProxy::setCallback(ICallbackPtr cb, float firstValue){
		std::future<void> future = async_setCallback(cb, firstValue);
		future.get();
	}
	ICallbackPtr EchoManagerProxy::getCallback(){
		std::future<ICallbackPtr> future = async_getCallback();
		return future.get();
	}
	void EchoManagerProxy::throwUnknownException(){
		std::future<void> future = async_throwUnknownException();
		future.get();
	}
	
	
	class Echo_BOOL__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		bool	_value;
		std::promise<bool>	promise;
	public:
		Echo_BOOL__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, bool value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo_BOOL__RemoteCall()
		{
		}
		
		std::future<bool> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			bool _result;
			response.stream() >> _result;
			promise.set_value(_result);
		}
	};
	std::future<bool> EchoManagerProxy::async_echo(bool value){
		Echo_BOOL__RemoteCall* call = new Echo_BOOL__RemoteCall(getCommunicator(), getMethod("Echo_BOOL_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo_BYTE__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		::mora::int8	_value;
		std::promise<::mora::int8>	promise;
	public:
		Echo_BYTE__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, ::mora::int8 value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo_BYTE__RemoteCall()
		{
		}
		
		std::future<::mora::int8> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			::mora::int8 _result;
			response.stream() >> _result;
			promise.set_value(_result);
		}
	};
	std::future<::mora::int8> EchoManagerProxy::async_echo(::mora::int8 value){
		Echo_BYTE__RemoteCall* call = new Echo_BYTE__RemoteCall(getCommunicator(), getMethod("Echo_BYTE_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo_SHORT__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		::mora::int16	_value;
		std::promise<::mora::int16>	promise;
	public:
		Echo_SHORT__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, ::mora::int16 value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo_SHORT__RemoteCall()
		{
		}
		
		std::future<::mora::int16> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			::mora::int16 _result;
			response.stream() >> _result;
			promise.set_value(_result);
		}
	};
	std::future<::mora::int16> EchoManagerProxy::async_echo(::mora::int16 value){
		Echo_SHORT__RemoteCall* call = new Echo_SHORT__RemoteCall(getCommunicator(), getMethod("Echo_SHORT_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo_INTEGER__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		::mora::int32	_value;
		std::promise<::mora::int32>	promise;
	public:
		Echo_INTEGER__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, ::mora::int32 value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo_INTEGER__RemoteCall()
		{
		}
		
		std::future<::mora::int32> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			::mora::int32 _result;
			response.stream() >> _result;
			promise.set_value(_result);
		}
	};
	std::future<::mora::int32> EchoManagerProxy::async_echo(::mora::int32 value){
		Echo_INTEGER__RemoteCall* call = new Echo_INTEGER__RemoteCall(getCommunicator(), getMethod("Echo_INTEGER_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo_LONG__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		::mora::int64	_value;
		std::promise<::mora::int64>	promise;
	public:
		Echo_LONG__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, ::mora::int64 value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo_LONG__RemoteCall()
		{
		}
		
		std::future<::mora::int64> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			::mora::int64 _result;
			response.stream() >> _result;
			promise.set_value(_result);
		}
	};
	std::future<::mora::int64> EchoManagerProxy::async_echo(::mora::int64 value){
		Echo_LONG__RemoteCall* call = new Echo_LONG__RemoteCall(getCommunicator(), getMethod("Echo_LONG_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo_FLOAT__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		float	_value;
		std::promise<float>	promise;
	public:
		Echo_FLOAT__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, float value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo_FLOAT__RemoteCall()
		{
		}
		
		std::future<float> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			float _result;
			response.stream() >> _result;
			promise.set_value(_result);
		}
	};
	std::future<float> EchoManagerProxy::async_echo(float value){
		Echo_FLOAT__RemoteCall* call = new Echo_FLOAT__RemoteCall(getCommunicator(), getMethod("Echo_FLOAT_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo_DOUBLE__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		double	_value;
		std::promise<double>	promise;
	public:
		Echo_DOUBLE__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, double value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo_DOUBLE__RemoteCall()
		{
		}
		
		std::future<double> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			double _result;
			response.stream() >> _result;
			promise.set_value(_result);
		}
	};
	std::future<double> EchoManagerProxy::async_echo(double value){
		Echo_DOUBLE__RemoteCall* call = new Echo_DOUBLE__RemoteCall(getCommunicator(), getMethod("Echo_DOUBLE_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo_STRING__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		std::string	_value;
		std::promise<std::string>	promise;
	public:
		Echo_STRING__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, std::string value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo_STRING__RemoteCall()
		{
		}
		
		std::future<std::string> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			std::string _result;
			response.stream() >> _result;
			promise.set_value(_result);
		}
	};
	std::future<std::string> EchoManagerProxy::async_echo(std::string value){
		Echo_STRING__RemoteCall* call = new Echo_STRING__RemoteCall(getCommunicator(), getMethod("Echo_STRING_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo_MyEnum__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		MyEnum	_value;
		std::promise<MyEnum>	promise;
	public:
		Echo_MyEnum__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, MyEnum value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo_MyEnum__RemoteCall()
		{
		}
		
		std::future<MyEnum> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			MyEnumUtil::write(_value, mMethodCall->stream);
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			MyEnum _result = MyEnumUtil::read(response.stream());
			promise.set_value(_result);
		}
	};
	std::future<MyEnum> EchoManagerProxy::async_echo(MyEnum value){
		Echo_MyEnum__RemoteCall* call = new Echo_MyEnum__RemoteCall(getCommunicator(), getMethod("Echo_MyEnum_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo_SimpleStruct__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		SimpleStruct*	_value;
		std::promise<SimpleStruct*>	promise;
	public:
		Echo_SimpleStruct__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, SimpleStruct* value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo_SimpleStruct__RemoteCall()
		{
		}
		
		std::future<SimpleStruct*> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			SimpleStruct::write(_value, mMethodCall->stream);
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			SimpleStruct* _result = SimpleStruct::read(response.stream());
			promise.set_value(_result);
		}
	};
	std::future<SimpleStruct*> EchoManagerProxy::async_echo(SimpleStruct* value){
		Echo_SimpleStruct__RemoteCall* call = new Echo_SimpleStruct__RemoteCall(getCommunicator(), getMethod("Echo_SimpleStruct_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo_ListStruct__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		ListStruct*	_value;
		std::promise<ListStruct*>	promise;
	public:
		Echo_ListStruct__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, ListStruct* value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo_ListStruct__RemoteCall()
		{
		}
		
		std::future<ListStruct*> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			ListStruct::write(_value, mMethodCall->stream);
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			ListStruct* _result = ListStruct::read(response.stream());
			promise.set_value(_result);
		}
	};
	std::future<ListStruct*> EchoManagerProxy::async_echo(ListStruct* value){
		Echo_ListStruct__RemoteCall* call = new Echo_ListStruct__RemoteCall(getCommunicator(), getMethod("Echo_ListStruct_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo1_BoolList__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		std::vector<bool>	_value;
		std::promise<std::vector<bool>>	promise;
	public:
		Echo1_BoolList__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, std::vector<bool> value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo1_BoolList__RemoteCall()
		{
		}
		
		std::future<std::vector<bool>> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			std::vector<bool> _result;
			response.stream() >> _result;
			promise.set_value(_result);
		}
	};
	std::future<std::vector<bool>> EchoManagerProxy::async_echo1(std::vector<bool> value){
		Echo1_BoolList__RemoteCall* call = new Echo1_BoolList__RemoteCall(getCommunicator(), getMethod("Echo1_BoolList_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo2_ByteList__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		std::vector<::mora::int8>	_value;
		std::promise<std::vector<::mora::int8>>	promise;
	public:
		Echo2_ByteList__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, std::vector<::mora::int8> value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo2_ByteList__RemoteCall()
		{
		}
		
		std::future<std::vector<::mora::int8>> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			std::vector<::mora::int8> _result;
			response.stream() >> _result;
			promise.set_value(_result);
		}
	};
	std::future<std::vector<::mora::int8>> EchoManagerProxy::async_echo2(std::vector<::mora::int8> value){
		Echo2_ByteList__RemoteCall* call = new Echo2_ByteList__RemoteCall(getCommunicator(), getMethod("Echo2_ByteList_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo3_ShortList__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		std::vector<::mora::int16>	_value;
		std::promise<std::vector<::mora::int16>>	promise;
	public:
		Echo3_ShortList__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, std::vector<::mora::int16> value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo3_ShortList__RemoteCall()
		{
		}
		
		std::future<std::vector<::mora::int16>> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			std::vector<::mora::int16> _result;
			response.stream() >> _result;
			promise.set_value(_result);
		}
	};
	std::future<std::vector<::mora::int16>> EchoManagerProxy::async_echo3(std::vector<::mora::int16> value){
		Echo3_ShortList__RemoteCall* call = new Echo3_ShortList__RemoteCall(getCommunicator(), getMethod("Echo3_ShortList_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo4_IntList__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		std::vector<::mora::int32>	_value;
		std::promise<std::vector<::mora::int32>>	promise;
	public:
		Echo4_IntList__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, std::vector<::mora::int32> value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo4_IntList__RemoteCall()
		{
		}
		
		std::future<std::vector<::mora::int32>> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			std::vector<::mora::int32> _result;
			response.stream() >> _result;
			promise.set_value(_result);
		}
	};
	std::future<std::vector<::mora::int32>> EchoManagerProxy::async_echo4(std::vector<::mora::int32> value){
		Echo4_IntList__RemoteCall* call = new Echo4_IntList__RemoteCall(getCommunicator(), getMethod("Echo4_IntList_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo5_LongList__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		std::vector<::mora::int64>	_value;
		std::promise<std::vector<::mora::int64>>	promise;
	public:
		Echo5_LongList__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, std::vector<::mora::int64> value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo5_LongList__RemoteCall()
		{
		}
		
		std::future<std::vector<::mora::int64>> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			std::vector<::mora::int64> _result;
			response.stream() >> _result;
			promise.set_value(_result);
		}
	};
	std::future<std::vector<::mora::int64>> EchoManagerProxy::async_echo5(std::vector<::mora::int64> value){
		Echo5_LongList__RemoteCall* call = new Echo5_LongList__RemoteCall(getCommunicator(), getMethod("Echo5_LongList_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo6_FloatList__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		std::vector<float>	_value;
		std::promise<std::vector<float>>	promise;
	public:
		Echo6_FloatList__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, std::vector<float> value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo6_FloatList__RemoteCall()
		{
		}
		
		std::future<std::vector<float>> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			std::vector<float> _result;
			response.stream() >> _result;
			promise.set_value(_result);
		}
	};
	std::future<std::vector<float>> EchoManagerProxy::async_echo6(std::vector<float> value){
		Echo6_FloatList__RemoteCall* call = new Echo6_FloatList__RemoteCall(getCommunicator(), getMethod("Echo6_FloatList_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo7_DoubleList__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		std::vector<double>	_value;
		std::promise<std::vector<double>>	promise;
	public:
		Echo7_DoubleList__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, std::vector<double> value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo7_DoubleList__RemoteCall()
		{
		}
		
		std::future<std::vector<double>> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			std::vector<double> _result;
			response.stream() >> _result;
			promise.set_value(_result);
		}
	};
	std::future<std::vector<double>> EchoManagerProxy::async_echo7(std::vector<double> value){
		Echo7_DoubleList__RemoteCall* call = new Echo7_DoubleList__RemoteCall(getCommunicator(), getMethod("Echo7_DoubleList_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo8_StringList__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		std::vector<std::string>	_value;
		std::promise<std::vector<std::string>>	promise;
	public:
		Echo8_StringList__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, std::vector<std::string> value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo8_StringList__RemoteCall()
		{
		}
		
		std::future<std::vector<std::string>> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			mMethodCall->stream << _value;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			std::vector<std::string> _result;
			response.stream() >> _result;
			promise.set_value(_result);
		}
	};
	std::future<std::vector<std::string>> EchoManagerProxy::async_echo8(std::vector<std::string> value){
		Echo8_StringList__RemoteCall* call = new Echo8_StringList__RemoteCall(getCommunicator(), getMethod("Echo8_StringList_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo9_MyEnumList__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		std::vector<MyEnum>	_value;
		std::promise<std::vector<MyEnum>>	promise;
	public:
		Echo9_MyEnumList__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, std::vector<MyEnum> value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo9_MyEnumList__RemoteCall()
		{
		}
		
		std::future<std::vector<MyEnum>> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			MyEnumUtil::write(_value, mMethodCall->stream);
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			std::vector<MyEnum> _result = MyEnumUtil::readList(response.stream());
			promise.set_value(_result);
		}
	};
	std::future<std::vector<MyEnum>> EchoManagerProxy::async_echo9(std::vector<MyEnum> value){
		Echo9_MyEnumList__RemoteCall* call = new Echo9_MyEnumList__RemoteCall(getCommunicator(), getMethod("Echo9_MyEnumList_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo10_SimpleStructList__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		std::vector<SimpleStruct*>	_value;
		std::promise<std::vector<SimpleStruct*>>	promise;
	public:
		Echo10_SimpleStructList__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, std::vector<SimpleStruct*> value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo10_SimpleStructList__RemoteCall()
		{
		}
		
		std::future<std::vector<SimpleStruct*>> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			SimpleStruct::write(_value, mMethodCall->stream);
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			std::vector<SimpleStruct*> _result = SimpleStruct::readList(response.stream());
			promise.set_value(_result);
		}
	};
	std::future<std::vector<SimpleStruct*>> EchoManagerProxy::async_echo10(std::vector<SimpleStruct*> value){
		Echo10_SimpleStructList__RemoteCall* call = new Echo10_SimpleStructList__RemoteCall(getCommunicator(), getMethod("Echo10_SimpleStructList_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class Echo11_ListListStruct__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		std::vector<ListStruct*>	_value;
		std::promise<std::vector<ListStruct*>>	promise;
	public:
		Echo11_ListListStruct__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, std::vector<ListStruct*> value)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _value(value)
		{
		}
		virtual ~Echo11_ListListStruct__RemoteCall()
		{
		}
		
		std::future<std::vector<ListStruct*>> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			ListStruct::write(_value, mMethodCall->stream);
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			std::vector<ListStruct*> _result = ListStruct::readList(response.stream());
			promise.set_value(_result);
		}
	};
	std::future<std::vector<ListStruct*>> EchoManagerProxy::async_echo11(std::vector<ListStruct*> value){
		Echo11_ListListStruct__RemoteCall* call = new Echo11_ListListStruct__RemoteCall(getCommunicator(), getMethod("Echo11_ListListStruct_"), value);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class SetCallback_Callback_FLOAT__RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		ICallbackPtr	_cb;
		float	_firstValue;
		std::promise<void>	promise;
	public:
		SetCallback_Callback_FLOAT__RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod, ICallbackPtr cb, float firstValue)
			:	::mora::IRemoteMethodCall(communicator, targetMethod), _cb(cb), _firstValue(firstValue)
		{
		}
		virtual ~SetCallback_Callback_FLOAT__RemoteCall()
		{
		}
		
		std::future<void> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			//Do the encoding
			CallbackAdapter::write(_cb, mMethodCall->stream, mCommunicator);
			mMethodCall->stream << _firstValue;
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			promise.set_value();
		}
	};
	std::future<void> EchoManagerProxy::async_setCallback(ICallbackPtr cb, float firstValue){
		SetCallback_Callback_FLOAT__RemoteCall* call = new SetCallback_Callback_FLOAT__RemoteCall(getCommunicator(), getMethod("SetCallback_Callback_FLOAT_"), cb, firstValue);
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class GetCallback_RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		std::promise<ICallbackPtr>	promise;
	public:
		GetCallback_RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod)
			:	::mora::IRemoteMethodCall(communicator, targetMethod)
		{
		}
		virtual ~GetCallback_RemoteCall()
		{
		}
		
		std::future<ICallbackPtr> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			ICallbackPtr _result = CallbackProxy::read(response.stream(), mCommunicator);
			promise.set_value(_result);
		}
	};
	std::future<ICallbackPtr> EchoManagerProxy::async_getCallback(){
		GetCallback_RemoteCall* call = new GetCallback_RemoteCall(getCommunicator(), getMethod("GetCallback"));
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	class ThrowUnknownException_RemoteCall : public ::mora::IRemoteMethodCall {
	private:
		std::promise<void>	promise;
	public:
		ThrowUnknownException_RemoteCall(::mora::Communicator* communicator, ::mora::RemoteMethod* targetMethod)
			:	::mora::IRemoteMethodCall(communicator, targetMethod)
		{
		}
		virtual ~ThrowUnknownException_RemoteCall()
		{
		}
		
		std::future<void> invoke() {
			mMethodCall = mCommunicator->createCall(mTargetMethod, mCommunicator->generateShortMagic());
			
			mCommunicator->call(this);
			return promise.get_future();
		}
		void handleResult(::mora::InMethodResponse& response) {
			promise.set_value();
		}
	};
	std::future<void> EchoManagerProxy::async_throwUnknownException(){
		ThrowUnknownException_RemoteCall* call = new ThrowUnknownException_RemoteCall(getCommunicator(), getMethod("ThrowUnknownException"));
		//the call will be removed by the communicator, after receiving the response
		return call->invoke();
	}
	


} /*de*/ } /*sos*/ } /*mora*/ } /*examples*/ 
