
using System.IO;
using Mora.Com;
using System.Collections;
using System.Collections.Generic;
using Mora;
using Mora.Stream;
using System;
using System.Threading.Tasks;

namespace De.Sos.MORA.Examples.Serialize {
	
	public class CallbackRPC {
		
		public static readonly string TYPE_IDENTIFIER = "CALLBACK";
		
		#region IFace
		public interface IFace {
			void onEcho(float value);
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
			
			private RemoteMethod[] mMethods = new RemoteMethod[1];
			
			public Proxy(RemoteObject remoteObject, Communicator communicator) 
				:	base(remoteObject, communicator)
			{
			}
			
			public override string GetTypeIdentifier()
			{
				return CallbackRPC.TYPE_IDENTIFIER;
			}
						
			
			private class RemoteCall_OnEcho_FLOAT_ : RemoteVoidMethodCall 
			{
				
				private float mValue;
				
				public RemoteCall_OnEcho_FLOAT_(Communicator c, RemoteMethod rm, float value)
					:	base(c, rm) 
				{
					mValue = value;
				}
				
				protected override void WriteParameters(IMoraOutputStream _oStream, Communicator _communicator)
				{
					_oStream.Write(mValue);
				}
				
			}
			
			public Task Async_onEcho(float value)
			{
				int methodIndex = 0;
				RemoteMethod method = mMethods[methodIndex];
				if (method == null)
				{
					mMethods[methodIndex] = method = CreateRemoteMethod("OnEcho_FLOAT_");
				}
				RemoteCall_OnEcho_FLOAT_ call = new RemoteCall_OnEcho_FLOAT_(Communicator, method, value );
				return call.invoke();
			}
			
			public void onEcho(float value){
				Task _task = Async_onEcho(value);
				_task.Wait();
			}
		}
		#endregion
		
		#region Adapter
		public class Adapter : AbstractAdapter<IFace> {
			private static Dictionary<string, IMethodInvokation<IFace>> sMethods = new Dictionary<string, IMethodInvokation<IFace>>();
			static Adapter() {
				sMethods.Add("_getType_", new _getType_Invoker());
				sMethods.Add("OnEcho_FLOAT_", new OnEcho_FLOAT_Invoker());
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
					oStream.Write(CallbackRPC.TYPE_IDENTIFIER);
				}
			}
			
			private class OnEcho_FLOAT_Invoker : IMethodInvokation<IFace> {
				public void Invoke(IFace _deleg, IMoraInputStream _iStream, IMoraOutputStream _oStream, Communicator _communicator)
				{
					float value;
					_iStream.Read(out value);
					_deleg.onEcho(value);
				}
			}
			#endregion
		}
		#endregion
	}
}
