package de.sos.mora.examples.serialize;

import de.sos.mora.Common;
import de.sos.mora.IMoraAdapter;
import de.sos.mora.IMoraProxy;
import de.sos.mora.RemoteMethodCall;
import de.sos.mora.RemoteObjects.RemoteMethod;
import de.sos.mora.RemoteObjects.RemoteObject;
import de.sos.mora.com.Communicator;
import de.sos.mora.com.Communicator.PROTOCOL;
import de.sos.mora.com.MoraMessages.InMethodRespond;
import de.sos.mora.com.MoraMessages.OutMethodCall;
import de.sos.mora.exceptions.MoraAdapterException;
import de.sos.mora.exceptions.MoraException;
import de.sos.mora.exceptions.MoraInvalidAddressException;
import de.sos.mora.exceptions.MoraProxyException;
import de.sos.mora.stream.IMoraInputStream;
import de.sos.mora.stream.IMoraOutputStream;
import de.sos.mora.types.IMoraTypeEncoder;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class CallbackRPC {
	
	//////////////////////////	IFace	////////////////////////////
	public interface IFace {
		public void onEcho(float value);
	}
	
	////////////////////////// User Interaction ////////////////////////////
	public static Adapter publishAdapter(String identifier, IFace impl, Communicator communicator) throws MoraAdapterException {
		if (communicator.hasAdapter(identifier)) {
			if (communicator.getAdapter(identifier).getDelegate() == impl) {
				return communicator.getAdapter(identifier);
			}
			else
				throw new MoraAdapterException("An Adapter with identifier: " + identifier + " already exists");
		}
		Adapter adapter = new Adapter(communicator, impl, identifier);
		return communicator.registerAdapter(adapter);
	}
	public static IFace createProxy(Communicator communicator, final String remoteObjectAddress) throws MoraInvalidAddressException {
		return createProxy(communicator, RemoteObject.createRemoteObject(remoteObjectAddress));
	}
	public static IFace createProxy(Communicator communicator, final RemoteObject remoteObject) throws MoraInvalidAddressException {
		Proxy proxy = new Proxy(communicator, remoteObject);
		if (proxy.checkType())
			return proxy;
		return null;
	}
	
	
	
	////////////////////////// Internal Stuff ////////////////////////////
	public static final String			TYPE_IDENTIFIER = "CALLBACK";
	
	public static void write(final IFace in, IMoraOutputStream stream, Communicator communicator) throws IOException {
		if (in == null)
			stream.writeByte(Common.STRUCT_NULL);
		else
			stream.writeByte(Common.STRUCT_START);
		Adapter adapter = null;
		if (in instanceof Adapter) {
			adapter = (Adapter)in;
		}else {
			//create an adapter with random id
			try {
				adapter = publishAdapter(UUID.randomUUID().toString(), in, communicator);
			} catch (MoraAdapterException e) {
				throw new IOException("Failed to publish adapter");
			}
		}
		assert(adapter != null);
		final String qid = adapter.getQualifiedIdentifier();
		stream.writeString(qid);
	}
	
	public static Proxy read(IMoraInputStream stream, Communicator communicator) throws IOException {
		byte flag = stream.readByte();
		if (flag == Common.STRUCT_NULL)
			return null;
		assert(flag == Common.STRUCT_START);
		
		final String quid = stream.readString();
		Proxy proxy = communicator.getProxy(quid);
		if (proxy == null) {
			try {
				proxy = new Proxy(communicator, RemoteObject.createRemoteObject(quid));
				communicator.registerProxy(proxy);
			} catch (MoraInvalidAddressException e) {
				e.printStackTrace();
				return null;
			}				
		}
		return proxy;	
	}
	public static class Proxy extends IMoraProxy<IFace> implements IFace {
		private RemoteMethod[] 		mRemoteMethods = new RemoteMethod[1];
		
		public Proxy(Communicator communicator, RemoteObject remoteObject) throws MoraInvalidAddressException {
			super(communicator, remoteObject);
		}
		@Override
		public String getTypeIdentifier() { return TYPE_IDENTIFIER; }
	
			private static class RemoteCall_OnEcho_FLOAT_ extends RemoteMethodCall<Void> {
				private final float mValue;
	
				public RemoteCall_OnEcho_FLOAT_(Communicator c, RemoteMethod rm, float value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeFloat(mValue);
				}
	
				@Override
				protected Void decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					return null;
				}
				
			}
					
			public CompletableFuture<Void> async_onEcho(float value)  throws MoraException {
				final int methodIndex = 0;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("OnEcho_FLOAT_");
				RemoteCall_OnEcho_FLOAT_ call = new RemoteCall_OnEcho_FLOAT_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public void onEcho(float value){
				try{
					CompletableFuture<Void> future = async_onEcho(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					future.get();
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	}
	public static class Adapter extends IMoraAdapter<IFace> implements IFace {
		private static HashMap<String, IMethodInvokation<IFace>>	sMethods = new HashMap<>();
		static {
			sMethods.put("_getType_", new _getType_Invoker());
			
			sMethods.put("OnEcho_FLOAT_", new OnEcho_FLOAT_Invoker());
		}
		protected Adapter(Communicator communicator, IFace delegate, final String identifier) {
			super(communicator, delegate, identifier);
		}
		@Override
		protected IMethodInvokation<IFace> getInvoker(String methodName) {
			return sMethods.get(methodName);
		}
		public void onEcho(float value){
			mDelegate.onEcho(value);
		}
				
		private static class _getType_Invoker implements IMethodInvokation<IFace> {
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				os.writeString(TYPE_IDENTIFIER);
			}
		}
		private static class OnEcho_FLOAT_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final float value = is.readFloat();
				delegate.onEcho(value);
			}
		}
	}
}
