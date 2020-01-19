
using System.IO;
using Mora.Com;
using System.Collections;
using Mora;
using System.Collections.Generic;
using Mora.Stream;
using System;
using System.Threading.Tasks;

namespace De.Sos.MORA.Examples.Serialize {
	
	public class EchoManagerRPC {
		
		public static readonly string TYPE_IDENTIFIER = "ECHOMANAGER";
		
		#region IFace
		public interface IFace {
			bool echo(bool value);
			byte echo(byte value);
			short echo(short value);
			int echo(int value);
			long echo(long value);
			float echo(float value);
			double echo(double value);
			string echo(string value);
			MyEnum echo(MyEnum value);
			SimpleStruct echo(SimpleStruct value);
			ListStruct echo(ListStruct value);
			List<bool> echo1(List<bool> value);
			List<byte> echo2(List<byte> value);
			List<short> echo3(List<short> value);
			List<int> echo4(List<int> value);
			List<long> echo5(List<long> value);
			List<float> echo6(List<float> value);
			List<double> echo7(List<double> value);
			List<string> echo8(List<string> value);
			List<MyEnum> echo9(List<MyEnum> value);
			List<SimpleStruct> echo10(List<SimpleStruct> value);
			List<ListStruct> echo11(List<ListStruct> value);
			void setCallback(CallbackRPC.IFace cb, float firstValue);
			CallbackRPC.IFace getCallback();
			void throwUnknownException();
		}
		#endregion
		
		#region Creation
		public static Adapter publishAdapter(string identifier, IFace impl, Communicator communicator)
		{
			if (communicator.hasAdapter(identifier))
			{
				if (communicator.getAdapter(identifier).represents(impl))
					return (Adapter)communicator.getAdapter(identifier);
				throw new MoraAdapterException("An Adapter with identifier: " + identifier + " already exists");
			}
			Adapter result = new Adapter(impl, identifier, communicator);
			return communicator.registerAdapter(result);
		}
		
		public static Proxy createProxy(Communicator communicator, String remoteObjectAddress) 
		{
			return createProxy(communicator, RemoteObject.create(remoteObjectAddress));
		}
		
		public static Proxy createProxy(Communicator communicator, RemoteObject remoteObject)
		{
			Proxy proxy = new Proxy(remoteObject, communicator);
			if (proxy.checkType())
				return proxy;
			return null;
		}
		#endregion
		
		#region Serialisation
		public static void Write(List<IFace> adapter, IMoraOutputStream stream, Communicator communicator)
		{
			stream.Write(adapter.Count);
			for (int i = 0; i < adapter.Count; i++)
				Write(adapter[i], stream, communicator);
		}
		public static void Write(IFace iface, IMoraOutputStream stream, Communicator communicator)
		{
			if (iface == null)
				stream.Write(Common.STRUCT_NULL);
			else
				stream.Write(Common.STRUCT_START);
				
			Adapter adapter = null;
			if (iface is Adapter) {
				adapter = (Adapter)iface;
			}else {
				//create an adapter with random id
				adapter = publishAdapter(System.Guid.NewGuid().ToString(), iface, communicator);
			}
			string qid = adapter.getQualifiedIdentifier();
			stream.Write(qid);
		}
		
		public static void Read(IMoraInputStream stream, Communicator communicator, out List<IFace> proxies)
		{
			int count;
			stream.Read(out count);
			proxies = new List<IFace>(count);
			for (int i = 0; i < count; i++){
				IFace proxy;
				Read(stream, communicator, out proxy);
				proxies.Add(proxy);	
			}
		}
		public static void Read(IMoraInputStream stream, Communicator communicator, out IFace result)
		{
			byte flag;
			stream.Read(out flag);
			if (flag == Common.STRUCT_NULL){
				result = null;
				return ;
			}
			string quid;
			stream.Read(out quid);
			IMoraProxy proxy = communicator.getProxy(quid);
			if (proxy == null){
				proxy = new Proxy(RemoteObject.create(quid), communicator);
				communicator.registerProxy(proxy);
			}
			result = proxy as Proxy;
		}
		
		#endregion
		
		#region Proxy
		public class Proxy : AbstractProxy<IFace>, IFace {
			
			private RemoteMethod[] mMethods = new RemoteMethod[25];
			
			public Proxy(RemoteObject remoteObject, Communicator communicator) 
				:	base(remoteObject, communicator)
			{
			}
			
			public override string GetTypeIdentifier()
			{
				return EchoManagerRPC.TYPE_IDENTIFIER;
			}
						
			
			private class RemoteCall_Echo_BOOL_ : RemoteMethodCall<bool> 
			{
				
				private bool mValue;
				
				public RemoteCall_Echo_BOOL_(Communicator c, RemoteMethod rm, bool value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					_oStream.Write(mValue);
				}
				
				protected override bool ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					bool _result;
					_iStream.Read(out _result);
					return _result;
				}
			}
			
			public Task<bool> Async_echo(bool value)
			{
				int methodIndex = 0;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo_BOOL_");
				}
				RemoteCall_Echo_BOOL_ call = new RemoteCall_Echo_BOOL_(Communicator, method, value );
				return call.invoke();
			}
			
			public bool echo(bool value){
				Task<bool> _task = Async_echo(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo_BYTE_ : RemoteMethodCall<byte> 
			{
				
				private byte mValue;
				
				public RemoteCall_Echo_BYTE_(Communicator c, RemoteMethod rm, byte value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					_oStream.Write(mValue);
				}
				
				protected override byte ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					byte _result;
					_iStream.Read(out _result);
					return _result;
				}
			}
			
			public Task<byte> Async_echo(byte value)
			{
				int methodIndex = 1;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo_BYTE_");
				}
				RemoteCall_Echo_BYTE_ call = new RemoteCall_Echo_BYTE_(Communicator, method, value );
				return call.invoke();
			}
			
			public byte echo(byte value){
				Task<byte> _task = Async_echo(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo_SHORT_ : RemoteMethodCall<short> 
			{
				
				private short mValue;
				
				public RemoteCall_Echo_SHORT_(Communicator c, RemoteMethod rm, short value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					_oStream.Write(mValue);
				}
				
				protected override short ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					short _result;
					_iStream.Read(out _result);
					return _result;
				}
			}
			
			public Task<short> Async_echo(short value)
			{
				int methodIndex = 2;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo_SHORT_");
				}
				RemoteCall_Echo_SHORT_ call = new RemoteCall_Echo_SHORT_(Communicator, method, value );
				return call.invoke();
			}
			
			public short echo(short value){
				Task<short> _task = Async_echo(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo_INTEGER_ : RemoteMethodCall<int> 
			{
				
				private int mValue;
				
				public RemoteCall_Echo_INTEGER_(Communicator c, RemoteMethod rm, int value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					_oStream.Write(mValue);
				}
				
				protected override int ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					int _result;
					_iStream.Read(out _result);
					return _result;
				}
			}
			
			public Task<int> Async_echo(int value)
			{
				int methodIndex = 3;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo_INTEGER_");
				}
				RemoteCall_Echo_INTEGER_ call = new RemoteCall_Echo_INTEGER_(Communicator, method, value );
				return call.invoke();
			}
			
			public int echo(int value){
				Task<int> _task = Async_echo(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo_LONG_ : RemoteMethodCall<long> 
			{
				
				private long mValue;
				
				public RemoteCall_Echo_LONG_(Communicator c, RemoteMethod rm, long value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					_oStream.Write(mValue);
				}
				
				protected override long ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					long _result;
					_iStream.Read(out _result);
					return _result;
				}
			}
			
			public Task<long> Async_echo(long value)
			{
				int methodIndex = 4;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo_LONG_");
				}
				RemoteCall_Echo_LONG_ call = new RemoteCall_Echo_LONG_(Communicator, method, value );
				return call.invoke();
			}
			
			public long echo(long value){
				Task<long> _task = Async_echo(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo_FLOAT_ : RemoteMethodCall<float> 
			{
				
				private float mValue;
				
				public RemoteCall_Echo_FLOAT_(Communicator c, RemoteMethod rm, float value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					_oStream.Write(mValue);
				}
				
				protected override float ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					float _result;
					_iStream.Read(out _result);
					return _result;
				}
			}
			
			public Task<float> Async_echo(float value)
			{
				int methodIndex = 5;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo_FLOAT_");
				}
				RemoteCall_Echo_FLOAT_ call = new RemoteCall_Echo_FLOAT_(Communicator, method, value );
				return call.invoke();
			}
			
			public float echo(float value){
				Task<float> _task = Async_echo(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo_DOUBLE_ : RemoteMethodCall<double> 
			{
				
				private double mValue;
				
				public RemoteCall_Echo_DOUBLE_(Communicator c, RemoteMethod rm, double value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					_oStream.Write(mValue);
				}
				
				protected override double ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					double _result;
					_iStream.Read(out _result);
					return _result;
				}
			}
			
			public Task<double> Async_echo(double value)
			{
				int methodIndex = 6;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo_DOUBLE_");
				}
				RemoteCall_Echo_DOUBLE_ call = new RemoteCall_Echo_DOUBLE_(Communicator, method, value );
				return call.invoke();
			}
			
			public double echo(double value){
				Task<double> _task = Async_echo(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo_STRING_ : RemoteMethodCall<string> 
			{
				
				private string mValue;
				
				public RemoteCall_Echo_STRING_(Communicator c, RemoteMethod rm, string value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					_oStream.Write(mValue);
				}
				
				protected override string ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					string _result;
					_iStream.Read(out _result);
					return _result;
				}
			}
			
			public Task<string> Async_echo(string value)
			{
				int methodIndex = 7;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo_STRING_");
				}
				RemoteCall_Echo_STRING_ call = new RemoteCall_Echo_STRING_(Communicator, method, value );
				return call.invoke();
			}
			
			public string echo(string value){
				Task<string> _task = Async_echo(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo_MyEnum_ : RemoteMethodCall<MyEnum> 
			{
				
				private MyEnum mValue;
				
				public RemoteCall_Echo_MyEnum_(Communicator c, RemoteMethod rm, MyEnum value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					MyEnumUtil.Write(mValue, _oStream, _communicator);
				}
				
				protected override MyEnum ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					MyEnum _result;
					MyEnumUtil.Read(_iStream, _communicator, out _result);
					return _result;
				}
			}
			
			public Task<MyEnum> Async_echo(MyEnum value)
			{
				int methodIndex = 8;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo_MyEnum_");
				}
				RemoteCall_Echo_MyEnum_ call = new RemoteCall_Echo_MyEnum_(Communicator, method, value );
				return call.invoke();
			}
			
			public MyEnum echo(MyEnum value){
				Task<MyEnum> _task = Async_echo(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo_SimpleStruct_ : RemoteMethodCall<SimpleStruct> 
			{
				
				private SimpleStruct mValue;
				
				public RemoteCall_Echo_SimpleStruct_(Communicator c, RemoteMethod rm, SimpleStruct value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					SimpleStruct.Write(mValue, _oStream, _communicator);
				}
				
				protected override SimpleStruct ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					SimpleStruct _result;
					SimpleStruct.Read(_iStream, _communicator, out _result);
					return _result;
				}
			}
			
			public Task<SimpleStruct> Async_echo(SimpleStruct value)
			{
				int methodIndex = 9;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo_SimpleStruct_");
				}
				RemoteCall_Echo_SimpleStruct_ call = new RemoteCall_Echo_SimpleStruct_(Communicator, method, value );
				return call.invoke();
			}
			
			public SimpleStruct echo(SimpleStruct value){
				Task<SimpleStruct> _task = Async_echo(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo_ListStruct_ : RemoteMethodCall<ListStruct> 
			{
				
				private ListStruct mValue;
				
				public RemoteCall_Echo_ListStruct_(Communicator c, RemoteMethod rm, ListStruct value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					ListStruct.Write(mValue, _oStream, _communicator);
				}
				
				protected override ListStruct ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					ListStruct _result;
					ListStruct.Read(_iStream, _communicator, out _result);
					return _result;
				}
			}
			
			public Task<ListStruct> Async_echo(ListStruct value)
			{
				int methodIndex = 10;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo_ListStruct_");
				}
				RemoteCall_Echo_ListStruct_ call = new RemoteCall_Echo_ListStruct_(Communicator, method, value );
				return call.invoke();
			}
			
			public ListStruct echo(ListStruct value){
				Task<ListStruct> _task = Async_echo(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo1_BoolList_ : RemoteMethodCall<List<bool>> 
			{
				
				private List<bool> mValue;
				
				public RemoteCall_Echo1_BoolList_(Communicator c, RemoteMethod rm, List<bool> value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					_oStream.Write(mValue);
				}
				
				protected override List<bool> ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					List<bool> _result;
					_iStream.Read(out _result);
					return _result;
				}
			}
			
			public Task<List<bool>> Async_echo1(List<bool> value)
			{
				int methodIndex = 11;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo1_BoolList_");
				}
				RemoteCall_Echo1_BoolList_ call = new RemoteCall_Echo1_BoolList_(Communicator, method, value );
				return call.invoke();
			}
			
			public List<bool> echo1(List<bool> value){
				Task<List<bool>> _task = Async_echo1(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo2_ByteList_ : RemoteMethodCall<List<byte>> 
			{
				
				private List<byte> mValue;
				
				public RemoteCall_Echo2_ByteList_(Communicator c, RemoteMethod rm, List<byte> value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					_oStream.Write(mValue);
				}
				
				protected override List<byte> ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					List<byte> _result;
					_iStream.Read(out _result);
					return _result;
				}
			}
			
			public Task<List<byte>> Async_echo2(List<byte> value)
			{
				int methodIndex = 12;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo2_ByteList_");
				}
				RemoteCall_Echo2_ByteList_ call = new RemoteCall_Echo2_ByteList_(Communicator, method, value );
				return call.invoke();
			}
			
			public List<byte> echo2(List<byte> value){
				Task<List<byte>> _task = Async_echo2(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo3_ShortList_ : RemoteMethodCall<List<short>> 
			{
				
				private List<short> mValue;
				
				public RemoteCall_Echo3_ShortList_(Communicator c, RemoteMethod rm, List<short> value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					_oStream.Write(mValue);
				}
				
				protected override List<short> ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					List<short> _result;
					_iStream.Read(out _result);
					return _result;
				}
			}
			
			public Task<List<short>> Async_echo3(List<short> value)
			{
				int methodIndex = 13;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo3_ShortList_");
				}
				RemoteCall_Echo3_ShortList_ call = new RemoteCall_Echo3_ShortList_(Communicator, method, value );
				return call.invoke();
			}
			
			public List<short> echo3(List<short> value){
				Task<List<short>> _task = Async_echo3(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo4_IntList_ : RemoteMethodCall<List<int>> 
			{
				
				private List<int> mValue;
				
				public RemoteCall_Echo4_IntList_(Communicator c, RemoteMethod rm, List<int> value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					_oStream.Write(mValue);
				}
				
				protected override List<int> ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					List<int> _result;
					_iStream.Read(out _result);
					return _result;
				}
			}
			
			public Task<List<int>> Async_echo4(List<int> value)
			{
				int methodIndex = 14;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo4_IntList_");
				}
				RemoteCall_Echo4_IntList_ call = new RemoteCall_Echo4_IntList_(Communicator, method, value );
				return call.invoke();
			}
			
			public List<int> echo4(List<int> value){
				Task<List<int>> _task = Async_echo4(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo5_LongList_ : RemoteMethodCall<List<long>> 
			{
				
				private List<long> mValue;
				
				public RemoteCall_Echo5_LongList_(Communicator c, RemoteMethod rm, List<long> value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					_oStream.Write(mValue);
				}
				
				protected override List<long> ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					List<long> _result;
					_iStream.Read(out _result);
					return _result;
				}
			}
			
			public Task<List<long>> Async_echo5(List<long> value)
			{
				int methodIndex = 15;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo5_LongList_");
				}
				RemoteCall_Echo5_LongList_ call = new RemoteCall_Echo5_LongList_(Communicator, method, value );
				return call.invoke();
			}
			
			public List<long> echo5(List<long> value){
				Task<List<long>> _task = Async_echo5(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo6_FloatList_ : RemoteMethodCall<List<float>> 
			{
				
				private List<float> mValue;
				
				public RemoteCall_Echo6_FloatList_(Communicator c, RemoteMethod rm, List<float> value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					_oStream.Write(mValue);
				}
				
				protected override List<float> ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					List<float> _result;
					_iStream.Read(out _result);
					return _result;
				}
			}
			
			public Task<List<float>> Async_echo6(List<float> value)
			{
				int methodIndex = 16;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo6_FloatList_");
				}
				RemoteCall_Echo6_FloatList_ call = new RemoteCall_Echo6_FloatList_(Communicator, method, value );
				return call.invoke();
			}
			
			public List<float> echo6(List<float> value){
				Task<List<float>> _task = Async_echo6(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo7_DoubleList_ : RemoteMethodCall<List<double>> 
			{
				
				private List<double> mValue;
				
				public RemoteCall_Echo7_DoubleList_(Communicator c, RemoteMethod rm, List<double> value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					_oStream.Write(mValue);
				}
				
				protected override List<double> ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					List<double> _result;
					_iStream.Read(out _result);
					return _result;
				}
			}
			
			public Task<List<double>> Async_echo7(List<double> value)
			{
				int methodIndex = 17;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo7_DoubleList_");
				}
				RemoteCall_Echo7_DoubleList_ call = new RemoteCall_Echo7_DoubleList_(Communicator, method, value );
				return call.invoke();
			}
			
			public List<double> echo7(List<double> value){
				Task<List<double>> _task = Async_echo7(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo8_StringList_ : RemoteMethodCall<List<string>> 
			{
				
				private List<string> mValue;
				
				public RemoteCall_Echo8_StringList_(Communicator c, RemoteMethod rm, List<string> value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					_oStream.Write(mValue);
				}
				
				protected override List<string> ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					List<string> _result;
					_iStream.Read(out _result);
					return _result;
				}
			}
			
			public Task<List<string>> Async_echo8(List<string> value)
			{
				int methodIndex = 18;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo8_StringList_");
				}
				RemoteCall_Echo8_StringList_ call = new RemoteCall_Echo8_StringList_(Communicator, method, value );
				return call.invoke();
			}
			
			public List<string> echo8(List<string> value){
				Task<List<string>> _task = Async_echo8(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo9_MyEnumList_ : RemoteMethodCall<List<MyEnum>> 
			{
				
				private List<MyEnum> mValue;
				
				public RemoteCall_Echo9_MyEnumList_(Communicator c, RemoteMethod rm, List<MyEnum> value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					MyEnumUtil.Write(mValue, _oStream, _communicator);
				}
				
				protected override List<MyEnum> ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					List<MyEnum> _result;
					MyEnumUtil.Read(_iStream, _communicator, out _result);
					return _result;
				}
			}
			
			public Task<List<MyEnum>> Async_echo9(List<MyEnum> value)
			{
				int methodIndex = 19;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo9_MyEnumList_");
				}
				RemoteCall_Echo9_MyEnumList_ call = new RemoteCall_Echo9_MyEnumList_(Communicator, method, value );
				return call.invoke();
			}
			
			public List<MyEnum> echo9(List<MyEnum> value){
				Task<List<MyEnum>> _task = Async_echo9(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo10_SimpleStructList_ : RemoteMethodCall<List<SimpleStruct>> 
			{
				
				private List<SimpleStruct> mValue;
				
				public RemoteCall_Echo10_SimpleStructList_(Communicator c, RemoteMethod rm, List<SimpleStruct> value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					SimpleStruct.Write(mValue, _oStream, _communicator);
				}
				
				protected override List<SimpleStruct> ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					List<SimpleStruct> _result;
					SimpleStruct.Read(_iStream, _communicator, out _result);
					return _result;
				}
			}
			
			public Task<List<SimpleStruct>> Async_echo10(List<SimpleStruct> value)
			{
				int methodIndex = 20;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo10_SimpleStructList_");
				}
				RemoteCall_Echo10_SimpleStructList_ call = new RemoteCall_Echo10_SimpleStructList_(Communicator, method, value );
				return call.invoke();
			}
			
			public List<SimpleStruct> echo10(List<SimpleStruct> value){
				Task<List<SimpleStruct>> _task = Async_echo10(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_Echo11_ListListStruct_ : RemoteMethodCall<List<ListStruct>> 
			{
				
				private List<ListStruct> mValue;
				
				public RemoteCall_Echo11_ListListStruct_(Communicator c, RemoteMethod rm, List<ListStruct> value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					ListStruct.Write(mValue, _oStream, _communicator);
				}
				
				protected override List<ListStruct> ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					List<ListStruct> _result;
					ListStruct.Read(_iStream, _communicator, out _result);
					return _result;
				}
			}
			
			public Task<List<ListStruct>> Async_echo11(List<ListStruct> value)
			{
				int methodIndex = 21;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("Echo11_ListListStruct_");
				}
				RemoteCall_Echo11_ListListStruct_ call = new RemoteCall_Echo11_ListListStruct_(Communicator, method, value );
				return call.invoke();
			}
			
			public List<ListStruct> echo11(List<ListStruct> value){
				Task<List<ListStruct>> _task = Async_echo11(value);
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_SetCallback_Callback_FLOAT_ : RemoteVoidMethodCall 
			{
				
				private CallbackRPC.IFace mCb;
				private float mFirstValue;
				
				public RemoteCall_SetCallback_Callback_FLOAT_(Communicator c, RemoteMethod rm, CallbackRPC.IFace cb, float firstValue)
					:	base(c, rm) 
				{
					mCb = cb;
					mFirstValue = firstValue;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					CallbackRPC.Write(mCb, _oStream, _communicator);
					_oStream.Write(mFirstValue);
				}
				
			}
			
			public Task Async_setCallback(CallbackRPC.IFace cb, float firstValue)
			{
				int methodIndex = 22;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("SetCallback_Callback_FLOAT_");
				}
				RemoteCall_SetCallback_Callback_FLOAT_ call = new RemoteCall_SetCallback_Callback_FLOAT_(Communicator, method, cb,  firstValue );
				return call.invoke();
			}
			
			public void setCallback(CallbackRPC.IFace cb, float firstValue){
				Task _task = Async_setCallback(cb, firstValue);
				_task.Wait();
			}
			
			private class RemoteCall_GetCallback : RemoteMethodCall<CallbackRPC.IFace> 
			{
				
				
				public RemoteCall_GetCallback(Communicator c, RemoteMethod rm)
					:	base(c, rm) 
				{
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
				}
				
				protected override CallbackRPC.IFace ReadReturnValue(IMoraInputStream _iStream, Communicator _communicator)
				{
					CallbackRPC.IFace _result;
					CallbackRPC.Read(_iStream, _communicator, out _result);
					return _result;
				}
			}
			
			public Task<CallbackRPC.IFace> Async_getCallback()
			{
				int methodIndex = 23;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("GetCallback");
				}
				RemoteCall_GetCallback call = new RemoteCall_GetCallback(Communicator, method);
				return call.invoke();
			}
			
			public CallbackRPC.IFace getCallback(){
				Task<CallbackRPC.IFace> _task = Async_getCallback();
				_task.Wait();
				return _task.Result;
			}
			
			private class RemoteCall_ThrowUnknownException : RemoteVoidMethodCall 
			{
				
				
				public RemoteCall_ThrowUnknownException(Communicator c, RemoteMethod rm)
					:	base(c, rm) 
				{
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
				}
				
			}
			
			public Task Async_throwUnknownException()
			{
				int methodIndex = 24;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("ThrowUnknownException");
				}
				RemoteCall_ThrowUnknownException call = new RemoteCall_ThrowUnknownException(Communicator, method);
				return call.invoke();
			}
			
			public void throwUnknownException(){
				Task _task = Async_throwUnknownException();
				_task.Wait();
			}
		}
		#endregion
		
		#region Adapter
		public class Adapter : AbstractAdapter<IFace> {
			private static Dictionary<string, IMethodInvokation<IFace>> sMethods = new Dictionary<string, IMethodInvokation<IFace>>();
			static Adapter() {
				sMethods.Add("_getType_", new _getType_Invoker());
				sMethods.Add("Echo_BOOL_", new Echo_BOOL_Invoker());
				sMethods.Add("Echo_BYTE_", new Echo_BYTE_Invoker());
				sMethods.Add("Echo_SHORT_", new Echo_SHORT_Invoker());
				sMethods.Add("Echo_INTEGER_", new Echo_INTEGER_Invoker());
				sMethods.Add("Echo_LONG_", new Echo_LONG_Invoker());
				sMethods.Add("Echo_FLOAT_", new Echo_FLOAT_Invoker());
				sMethods.Add("Echo_DOUBLE_", new Echo_DOUBLE_Invoker());
				sMethods.Add("Echo_STRING_", new Echo_STRING_Invoker());
				sMethods.Add("Echo_MyEnum_", new Echo_MyEnum_Invoker());
				sMethods.Add("Echo_SimpleStruct_", new Echo_SimpleStruct_Invoker());
				sMethods.Add("Echo_ListStruct_", new Echo_ListStruct_Invoker());
				sMethods.Add("Echo1_BoolList_", new Echo1_BoolList_Invoker());
				sMethods.Add("Echo2_ByteList_", new Echo2_ByteList_Invoker());
				sMethods.Add("Echo3_ShortList_", new Echo3_ShortList_Invoker());
				sMethods.Add("Echo4_IntList_", new Echo4_IntList_Invoker());
				sMethods.Add("Echo5_LongList_", new Echo5_LongList_Invoker());
				sMethods.Add("Echo6_FloatList_", new Echo6_FloatList_Invoker());
				sMethods.Add("Echo7_DoubleList_", new Echo7_DoubleList_Invoker());
				sMethods.Add("Echo8_StringList_", new Echo8_StringList_Invoker());
				sMethods.Add("Echo9_MyEnumList_", new Echo9_MyEnumList_Invoker());
				sMethods.Add("Echo10_SimpleStructList_", new Echo10_SimpleStructList_Invoker());
				sMethods.Add("Echo11_ListListStruct_", new Echo11_ListListStruct_Invoker());
				sMethods.Add("SetCallback_Callback_FLOAT_", new SetCallback_Callback_FLOAT_Invoker());
				sMethods.Add("GetCallback", new GetCallbackInvoker());
				sMethods.Add("ThrowUnknownException", new ThrowUnknownExceptionInvoker());
			}
			            		
			public Adapter(IFace deleg, string identifier, Communicator communicator)
				: base(deleg, identifier, communicator)
			{
			}
		
			protected override IMethodInvokation<IFace> getInvoker(string methodName)
			{
				return sMethods[methodName];
			}
		
			#region invoker
			
			private class _getType_Invoker : IMethodInvokation<IFace>
			{
				public void Invoke(IFace deleg, IMoraInputStream iStream, IMoraOutputStream oStream, Communicator communicator)
				{
					oStream.Write(EchoManagerRPC.TYPE_IDENTIFIER);
				}
			}
			
			private class Echo_BOOL_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					bool value;
					_iStream.Read(out value);
					bool _result_ = _deleg.echo(value);
					_oStream.Write(_result_);
				}
			}
			private class Echo_BYTE_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					byte value;
					_iStream.Read(out value);
					byte _result_ = _deleg.echo(value);
					_oStream.Write(_result_);
				}
			}
			private class Echo_SHORT_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					short value;
					_iStream.Read(out value);
					short _result_ = _deleg.echo(value);
					_oStream.Write(_result_);
				}
			}
			private class Echo_INTEGER_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					int value;
					_iStream.Read(out value);
					int _result_ = _deleg.echo(value);
					_oStream.Write(_result_);
				}
			}
			private class Echo_LONG_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					long value;
					_iStream.Read(out value);
					long _result_ = _deleg.echo(value);
					_oStream.Write(_result_);
				}
			}
			private class Echo_FLOAT_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					float value;
					_iStream.Read(out value);
					float _result_ = _deleg.echo(value);
					_oStream.Write(_result_);
				}
			}
			private class Echo_DOUBLE_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					double value;
					_iStream.Read(out value);
					double _result_ = _deleg.echo(value);
					_oStream.Write(_result_);
				}
			}
			private class Echo_STRING_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					string value;
					_iStream.Read(out value);
					string _result_ = _deleg.echo(value);
					_oStream.Write(_result_);
				}
			}
			private class Echo_MyEnum_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					MyEnum value;
					MyEnumUtil.Read(_iStream, _communicator, out value);
					MyEnum _result_ = _deleg.echo(value);
					MyEnumUtil.Write(_result_, _oStream, _communicator);
				}
			}
			private class Echo_SimpleStruct_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					SimpleStruct value;
					SimpleStruct.Read(_iStream, _communicator, out value);
					SimpleStruct _result_ = _deleg.echo(value);
					SimpleStruct.Write(_result_, _oStream, _communicator);
				}
			}
			private class Echo_ListStruct_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					ListStruct value;
					ListStruct.Read(_iStream, _communicator, out value);
					ListStruct _result_ = _deleg.echo(value);
					ListStruct.Write(_result_, _oStream, _communicator);
				}
			}
			private class Echo1_BoolList_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					List<bool> value;
					_iStream.Read(out value);
					List<bool> _result_ = _deleg.echo1(value);
					_oStream.Write(_result_);
				}
			}
			private class Echo2_ByteList_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					List<byte> value;
					_iStream.Read(out value);
					List<byte> _result_ = _deleg.echo2(value);
					_oStream.Write(_result_);
				}
			}
			private class Echo3_ShortList_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					List<short> value;
					_iStream.Read(out value);
					List<short> _result_ = _deleg.echo3(value);
					_oStream.Write(_result_);
				}
			}
			private class Echo4_IntList_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					List<int> value;
					_iStream.Read(out value);
					List<int> _result_ = _deleg.echo4(value);
					_oStream.Write(_result_);
				}
			}
			private class Echo5_LongList_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					List<long> value;
					_iStream.Read(out value);
					List<long> _result_ = _deleg.echo5(value);
					_oStream.Write(_result_);
				}
			}
			private class Echo6_FloatList_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					List<float> value;
					_iStream.Read(out value);
					List<float> _result_ = _deleg.echo6(value);
					_oStream.Write(_result_);
				}
			}
			private class Echo7_DoubleList_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					List<double> value;
					_iStream.Read(out value);
					List<double> _result_ = _deleg.echo7(value);
					_oStream.Write(_result_);
				}
			}
			private class Echo8_StringList_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					List<string> value;
					_iStream.Read(out value);
					List<string> _result_ = _deleg.echo8(value);
					_oStream.Write(_result_);
				}
			}
			private class Echo9_MyEnumList_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					List<MyEnum> value;
					MyEnumUtil.Read(_iStream, _communicator, out value);
					List<MyEnum> _result_ = _deleg.echo9(value);
					MyEnumUtil.Write(_result_, _oStream, _communicator);
				}
			}
			private class Echo10_SimpleStructList_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					List<SimpleStruct> value;
					SimpleStruct.Read(_iStream, _communicator, out value);
					List<SimpleStruct> _result_ = _deleg.echo10(value);
					SimpleStruct.Write(_result_, _oStream, _communicator);
				}
			}
			private class Echo11_ListListStruct_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					List<ListStruct> value;
					ListStruct.Read(_iStream, _communicator, out value);
					List<ListStruct> _result_ = _deleg.echo11(value);
					ListStruct.Write(_result_, _oStream, _communicator);
				}
			}
			private class SetCallback_Callback_FLOAT_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					CallbackRPC.IFace cb;
					CallbackRPC.Read(_iStream, _communicator, out cb);
					float firstValue;
					_iStream.Read(out firstValue);
					_deleg.setCallback(cb, firstValue);
				}
			}
			private class GetCallbackInvoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					CallbackRPC.IFace _result_ = _deleg.getCallback();
					CallbackRPC.Write(_result_, _oStream, _communicator);
				}
			}
			private class ThrowUnknownExceptionInvoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					_deleg.throwUnknownException();
				}
			}
			#endregion
		}
		#endregion
	}
}
