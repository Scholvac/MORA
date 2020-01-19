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
import de.sos.mora.examples.serialize.CallbackRPC;
import de.sos.mora.examples.serialize.ListStruct;
import de.sos.mora.examples.serialize.MyEnum;
import de.sos.mora.examples.serialize.SimpleStruct;
import de.sos.mora.exceptions.MoraAdapterException;
import de.sos.mora.exceptions.MoraException;
import de.sos.mora.exceptions.MoraInvalidAddressException;
import de.sos.mora.exceptions.MoraProxyException;
import de.sos.mora.list.BoolArrayList;
import de.sos.mora.list.ByteArrayList;
import de.sos.mora.list.DoubleArrayList;
import de.sos.mora.list.FloatArrayList;
import de.sos.mora.list.IntArrayList;
import de.sos.mora.list.LongArrayList;
import de.sos.mora.list.ShortArrayList;
import de.sos.mora.list.StringArrayList;
import de.sos.mora.stream.IMoraInputStream;
import de.sos.mora.stream.IMoraOutputStream;
import de.sos.mora.types.IMoraTypeEncoder;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class EchoManagerRPC {
	
	//////////////////////////	IFace	////////////////////////////
	public interface IFace {
		public boolean echo(boolean value);
		public byte echo(byte value);
		public short echo(short value);
		public int echo(int value);
		public long echo(long value);
		public float echo(float value);
		public double echo(double value);
		public String echo(String value);
		public MyEnum echo(MyEnum value);
		public SimpleStruct echo(SimpleStruct value);
		public ListStruct echo(ListStruct value);
		public BoolArrayList echo1(BoolArrayList value);
		public ByteArrayList echo2(ByteArrayList value);
		public ShortArrayList echo3(ShortArrayList value);
		public IntArrayList echo4(IntArrayList value);
		public LongArrayList echo5(LongArrayList value);
		public FloatArrayList echo6(FloatArrayList value);
		public DoubleArrayList echo7(DoubleArrayList value);
		public StringArrayList echo8(StringArrayList value);
		public List<MyEnum> echo9(List<MyEnum> value);
		public List<SimpleStruct> echo10(List<SimpleStruct> value);
		public List<ListStruct> echo11(List<ListStruct> value);
		public void setCallback(CallbackRPC.IFace cb, float firstValue);
		public CallbackRPC.IFace getCallback();
		public void throwUnknownException();
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
	public static final String			TYPE_IDENTIFIER = "ECHOMANAGER";
	
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
		private RemoteMethod[] 		mRemoteMethods = new RemoteMethod[25];
		
		public Proxy(Communicator communicator, RemoteObject remoteObject) throws MoraInvalidAddressException {
			super(communicator, remoteObject);
		}
		@Override
		public String getTypeIdentifier() { return TYPE_IDENTIFIER; }
	
			private static class RemoteCall_Echo_BOOL_ extends RemoteMethodCall<Boolean> {
				private final boolean mValue;
	
				public RemoteCall_Echo_BOOL_(Communicator c, RemoteMethod rm, boolean value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeBoolean(mValue);
				}
	
				@Override
				protected Boolean decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final boolean result = is.readBoolean();
					return result;
				}
				
			}
					
			public CompletableFuture<Boolean> async_echo(boolean value)  throws MoraException {
				final int methodIndex = 0;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo_BOOL_");
				RemoteCall_Echo_BOOL_ call = new RemoteCall_Echo_BOOL_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public boolean echo(boolean value){
				try{
					CompletableFuture<Boolean> future = async_echo(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final boolean res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo_BYTE_ extends RemoteMethodCall<Byte> {
				private final byte mValue;
	
				public RemoteCall_Echo_BYTE_(Communicator c, RemoteMethod rm, byte value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeByte(mValue);
				}
	
				@Override
				protected Byte decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final byte result = is.readByte();
					return result;
				}
				
			}
					
			public CompletableFuture<Byte> async_echo(byte value)  throws MoraException {
				final int methodIndex = 1;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo_BYTE_");
				RemoteCall_Echo_BYTE_ call = new RemoteCall_Echo_BYTE_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public byte echo(byte value){
				try{
					CompletableFuture<Byte> future = async_echo(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final byte res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo_SHORT_ extends RemoteMethodCall<Short> {
				private final short mValue;
	
				public RemoteCall_Echo_SHORT_(Communicator c, RemoteMethod rm, short value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeShort(mValue);
				}
	
				@Override
				protected Short decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final short result = is.readShort();
					return result;
				}
				
			}
					
			public CompletableFuture<Short> async_echo(short value)  throws MoraException {
				final int methodIndex = 2;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo_SHORT_");
				RemoteCall_Echo_SHORT_ call = new RemoteCall_Echo_SHORT_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public short echo(short value){
				try{
					CompletableFuture<Short> future = async_echo(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final short res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo_INTEGER_ extends RemoteMethodCall<Integer> {
				private final int mValue;
	
				public RemoteCall_Echo_INTEGER_(Communicator c, RemoteMethod rm, int value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeInteger(mValue);
				}
	
				@Override
				protected Integer decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final int result = is.readInteger();
					return result;
				}
				
			}
					
			public CompletableFuture<Integer> async_echo(int value)  throws MoraException {
				final int methodIndex = 3;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo_INTEGER_");
				RemoteCall_Echo_INTEGER_ call = new RemoteCall_Echo_INTEGER_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public int echo(int value){
				try{
					CompletableFuture<Integer> future = async_echo(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final int res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo_LONG_ extends RemoteMethodCall<Long> {
				private final long mValue;
	
				public RemoteCall_Echo_LONG_(Communicator c, RemoteMethod rm, long value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeLong(mValue);
				}
	
				@Override
				protected Long decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final long result = is.readLong();
					return result;
				}
				
			}
					
			public CompletableFuture<Long> async_echo(long value)  throws MoraException {
				final int methodIndex = 4;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo_LONG_");
				RemoteCall_Echo_LONG_ call = new RemoteCall_Echo_LONG_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public long echo(long value){
				try{
					CompletableFuture<Long> future = async_echo(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final long res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo_FLOAT_ extends RemoteMethodCall<Float> {
				private final float mValue;
	
				public RemoteCall_Echo_FLOAT_(Communicator c, RemoteMethod rm, float value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeFloat(mValue);
				}
	
				@Override
				protected Float decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final float result = is.readFloat();
					return result;
				}
				
			}
					
			public CompletableFuture<Float> async_echo(float value)  throws MoraException {
				final int methodIndex = 5;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo_FLOAT_");
				RemoteCall_Echo_FLOAT_ call = new RemoteCall_Echo_FLOAT_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public float echo(float value){
				try{
					CompletableFuture<Float> future = async_echo(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final float res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo_DOUBLE_ extends RemoteMethodCall<Double> {
				private final double mValue;
	
				public RemoteCall_Echo_DOUBLE_(Communicator c, RemoteMethod rm, double value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeDouble(mValue);
				}
	
				@Override
				protected Double decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final double result = is.readDouble();
					return result;
				}
				
			}
					
			public CompletableFuture<Double> async_echo(double value)  throws MoraException {
				final int methodIndex = 6;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo_DOUBLE_");
				RemoteCall_Echo_DOUBLE_ call = new RemoteCall_Echo_DOUBLE_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public double echo(double value){
				try{
					CompletableFuture<Double> future = async_echo(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final double res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo_STRING_ extends RemoteMethodCall<String> {
				private final String mValue;
	
				public RemoteCall_Echo_STRING_(Communicator c, RemoteMethod rm, String value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeString(mValue);
				}
	
				@Override
				protected String decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final String result = is.readString();
					return result;
				}
				
			}
					
			public CompletableFuture<String> async_echo(String value)  throws MoraException {
				final int methodIndex = 7;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo_STRING_");
				RemoteCall_Echo_STRING_ call = new RemoteCall_Echo_STRING_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public String echo(String value){
				try{
					CompletableFuture<String> future = async_echo(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final String res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo_MyEnum_ extends RemoteMethodCall<MyEnum> {
				private final MyEnum mValue;
	
				public RemoteCall_Echo_MyEnum_(Communicator c, RemoteMethod rm, MyEnum value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					MyEnum.write(mValue, os, communicator);
				}
	
				@Override
				protected MyEnum decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final MyEnum result = MyEnum.read(is, communicator);
					return result;
				}
				
			}
					
			public CompletableFuture<MyEnum> async_echo(MyEnum value)  throws MoraException {
				final int methodIndex = 8;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo_MyEnum_");
				RemoteCall_Echo_MyEnum_ call = new RemoteCall_Echo_MyEnum_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public MyEnum echo(MyEnum value){
				try{
					CompletableFuture<MyEnum> future = async_echo(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final MyEnum res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo_SimpleStruct_ extends RemoteMethodCall<SimpleStruct> {
				private final SimpleStruct mValue;
	
				public RemoteCall_Echo_SimpleStruct_(Communicator c, RemoteMethod rm, SimpleStruct value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					SimpleStruct.write(mValue, os, communicator);
				}
	
				@Override
				protected SimpleStruct decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final SimpleStruct result = SimpleStruct.read(is, communicator);
					return result;
				}
				
			}
					
			public CompletableFuture<SimpleStruct> async_echo(SimpleStruct value)  throws MoraException {
				final int methodIndex = 9;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo_SimpleStruct_");
				RemoteCall_Echo_SimpleStruct_ call = new RemoteCall_Echo_SimpleStruct_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public SimpleStruct echo(SimpleStruct value){
				try{
					CompletableFuture<SimpleStruct> future = async_echo(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final SimpleStruct res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo_ListStruct_ extends RemoteMethodCall<ListStruct> {
				private final ListStruct mValue;
	
				public RemoteCall_Echo_ListStruct_(Communicator c, RemoteMethod rm, ListStruct value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					ListStruct.write(mValue, os, communicator);
				}
	
				@Override
				protected ListStruct decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final ListStruct result = ListStruct.read(is, communicator);
					return result;
				}
				
			}
					
			public CompletableFuture<ListStruct> async_echo(ListStruct value)  throws MoraException {
				final int methodIndex = 10;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo_ListStruct_");
				RemoteCall_Echo_ListStruct_ call = new RemoteCall_Echo_ListStruct_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public ListStruct echo(ListStruct value){
				try{
					CompletableFuture<ListStruct> future = async_echo(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final ListStruct res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo1_BoolList_ extends RemoteMethodCall<BoolArrayList> {
				private final BoolArrayList mValue;
	
				public RemoteCall_Echo1_BoolList_(Communicator c, RemoteMethod rm, BoolArrayList value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeBoolean(mValue);
				}
	
				@Override
				protected BoolArrayList decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final BoolArrayList result = is.readBooleanList();
					return result;
				}
				
			}
					
			public CompletableFuture<BoolArrayList> async_echo1(BoolArrayList value)  throws MoraException {
				final int methodIndex = 11;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo1_BoolList_");
				RemoteCall_Echo1_BoolList_ call = new RemoteCall_Echo1_BoolList_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public BoolArrayList echo1(BoolArrayList value){
				try{
					CompletableFuture<BoolArrayList> future = async_echo1(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final BoolArrayList res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo2_ByteList_ extends RemoteMethodCall<ByteArrayList> {
				private final ByteArrayList mValue;
	
				public RemoteCall_Echo2_ByteList_(Communicator c, RemoteMethod rm, ByteArrayList value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeByte(mValue);
				}
	
				@Override
				protected ByteArrayList decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final ByteArrayList result = is.readByteList();
					return result;
				}
				
			}
					
			public CompletableFuture<ByteArrayList> async_echo2(ByteArrayList value)  throws MoraException {
				final int methodIndex = 12;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo2_ByteList_");
				RemoteCall_Echo2_ByteList_ call = new RemoteCall_Echo2_ByteList_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public ByteArrayList echo2(ByteArrayList value){
				try{
					CompletableFuture<ByteArrayList> future = async_echo2(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final ByteArrayList res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo3_ShortList_ extends RemoteMethodCall<ShortArrayList> {
				private final ShortArrayList mValue;
	
				public RemoteCall_Echo3_ShortList_(Communicator c, RemoteMethod rm, ShortArrayList value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeShort(mValue);
				}
	
				@Override
				protected ShortArrayList decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final ShortArrayList result = is.readShortList();
					return result;
				}
				
			}
					
			public CompletableFuture<ShortArrayList> async_echo3(ShortArrayList value)  throws MoraException {
				final int methodIndex = 13;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo3_ShortList_");
				RemoteCall_Echo3_ShortList_ call = new RemoteCall_Echo3_ShortList_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public ShortArrayList echo3(ShortArrayList value){
				try{
					CompletableFuture<ShortArrayList> future = async_echo3(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final ShortArrayList res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo4_IntList_ extends RemoteMethodCall<IntArrayList> {
				private final IntArrayList mValue;
	
				public RemoteCall_Echo4_IntList_(Communicator c, RemoteMethod rm, IntArrayList value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeInteger(mValue);
				}
	
				@Override
				protected IntArrayList decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final IntArrayList result = is.readIntegerList();
					return result;
				}
				
			}
					
			public CompletableFuture<IntArrayList> async_echo4(IntArrayList value)  throws MoraException {
				final int methodIndex = 14;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo4_IntList_");
				RemoteCall_Echo4_IntList_ call = new RemoteCall_Echo4_IntList_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public IntArrayList echo4(IntArrayList value){
				try{
					CompletableFuture<IntArrayList> future = async_echo4(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final IntArrayList res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo5_LongList_ extends RemoteMethodCall<LongArrayList> {
				private final LongArrayList mValue;
	
				public RemoteCall_Echo5_LongList_(Communicator c, RemoteMethod rm, LongArrayList value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeLong(mValue);
				}
	
				@Override
				protected LongArrayList decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final LongArrayList result = is.readLongList();
					return result;
				}
				
			}
					
			public CompletableFuture<LongArrayList> async_echo5(LongArrayList value)  throws MoraException {
				final int methodIndex = 15;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo5_LongList_");
				RemoteCall_Echo5_LongList_ call = new RemoteCall_Echo5_LongList_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public LongArrayList echo5(LongArrayList value){
				try{
					CompletableFuture<LongArrayList> future = async_echo5(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final LongArrayList res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo6_FloatList_ extends RemoteMethodCall<FloatArrayList> {
				private final FloatArrayList mValue;
	
				public RemoteCall_Echo6_FloatList_(Communicator c, RemoteMethod rm, FloatArrayList value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeFloat(mValue);
				}
	
				@Override
				protected FloatArrayList decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final FloatArrayList result = is.readFloatList();
					return result;
				}
				
			}
					
			public CompletableFuture<FloatArrayList> async_echo6(FloatArrayList value)  throws MoraException {
				final int methodIndex = 16;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo6_FloatList_");
				RemoteCall_Echo6_FloatList_ call = new RemoteCall_Echo6_FloatList_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public FloatArrayList echo6(FloatArrayList value){
				try{
					CompletableFuture<FloatArrayList> future = async_echo6(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final FloatArrayList res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo7_DoubleList_ extends RemoteMethodCall<DoubleArrayList> {
				private final DoubleArrayList mValue;
	
				public RemoteCall_Echo7_DoubleList_(Communicator c, RemoteMethod rm, DoubleArrayList value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeDouble(mValue);
				}
	
				@Override
				protected DoubleArrayList decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final DoubleArrayList result = is.readDoubleList();
					return result;
				}
				
			}
					
			public CompletableFuture<DoubleArrayList> async_echo7(DoubleArrayList value)  throws MoraException {
				final int methodIndex = 17;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo7_DoubleList_");
				RemoteCall_Echo7_DoubleList_ call = new RemoteCall_Echo7_DoubleList_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public DoubleArrayList echo7(DoubleArrayList value){
				try{
					CompletableFuture<DoubleArrayList> future = async_echo7(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final DoubleArrayList res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo8_StringList_ extends RemoteMethodCall<StringArrayList> {
				private final StringArrayList mValue;
	
				public RemoteCall_Echo8_StringList_(Communicator c, RemoteMethod rm, StringArrayList value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					os.writeString(mValue);
				}
	
				@Override
				protected StringArrayList decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final StringArrayList result = is.readStringList();
					return result;
				}
				
			}
					
			public CompletableFuture<StringArrayList> async_echo8(StringArrayList value)  throws MoraException {
				final int methodIndex = 18;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo8_StringList_");
				RemoteCall_Echo8_StringList_ call = new RemoteCall_Echo8_StringList_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public StringArrayList echo8(StringArrayList value){
				try{
					CompletableFuture<StringArrayList> future = async_echo8(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final StringArrayList res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo9_MyEnumList_ extends RemoteMethodCall<List<MyEnum>> {
				private final List<MyEnum> mValue;
	
				public RemoteCall_Echo9_MyEnumList_(Communicator c, RemoteMethod rm, List<MyEnum> value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					MyEnum.write(mValue, os, communicator);
				}
	
				@Override
				protected List<MyEnum> decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final List<MyEnum> result = MyEnum.readList(is, communicator);
					return result;
				}
				
			}
					
			public CompletableFuture<List<MyEnum>> async_echo9(List<MyEnum> value)  throws MoraException {
				final int methodIndex = 19;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo9_MyEnumList_");
				RemoteCall_Echo9_MyEnumList_ call = new RemoteCall_Echo9_MyEnumList_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public List<MyEnum> echo9(List<MyEnum> value){
				try{
					CompletableFuture<List<MyEnum>> future = async_echo9(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final List<MyEnum> res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo10_SimpleStructList_ extends RemoteMethodCall<List<SimpleStruct>> {
				private final List<SimpleStruct> mValue;
	
				public RemoteCall_Echo10_SimpleStructList_(Communicator c, RemoteMethod rm, List<SimpleStruct> value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					SimpleStruct.write(mValue, os, communicator);
				}
	
				@Override
				protected List<SimpleStruct> decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final List<SimpleStruct> result = SimpleStruct.readList(is, communicator);
					return result;
				}
				
			}
					
			public CompletableFuture<List<SimpleStruct>> async_echo10(List<SimpleStruct> value)  throws MoraException {
				final int methodIndex = 20;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo10_SimpleStructList_");
				RemoteCall_Echo10_SimpleStructList_ call = new RemoteCall_Echo10_SimpleStructList_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public List<SimpleStruct> echo10(List<SimpleStruct> value){
				try{
					CompletableFuture<List<SimpleStruct>> future = async_echo10(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final List<SimpleStruct> res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_Echo11_ListListStruct_ extends RemoteMethodCall<List<ListStruct>> {
				private final List<ListStruct> mValue;
	
				public RemoteCall_Echo11_ListListStruct_(Communicator c, RemoteMethod rm, List<ListStruct> value) {
					super(c, rm);
					mValue = value;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					ListStruct.write(mValue, os, communicator);
				}
	
				@Override
				protected List<ListStruct> decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final List<ListStruct> result = ListStruct.readList(is, communicator);
					return result;
				}
				
			}
					
			public CompletableFuture<List<ListStruct>> async_echo11(List<ListStruct> value)  throws MoraException {
				final int methodIndex = 21;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("Echo11_ListListStruct_");
				RemoteCall_Echo11_ListListStruct_ call = new RemoteCall_Echo11_ListListStruct_(getCommunicator(), mRemoteMethods[methodIndex], value );
				return call.invoke();
			}
	
			@Override
			public List<ListStruct> echo11(List<ListStruct> value){
				try{
					CompletableFuture<List<ListStruct>> future = async_echo11(value);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final List<ListStruct> res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_SetCallback_Callback_FLOAT_ extends RemoteMethodCall<Void> {
				private final CallbackRPC.IFace mCb;
				private final float mFirstValue;
	
				public RemoteCall_SetCallback_Callback_FLOAT_(Communicator c, RemoteMethod rm, CallbackRPC.IFace cb, float firstValue) {
					super(c, rm);
					mCb = cb;
					mFirstValue = firstValue;
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
					CallbackRPC.write(mCb, os, communicator);
					os.writeFloat(mFirstValue);
				}
	
				@Override
				protected Void decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					return null;
				}
				
			}
					
			public CompletableFuture<Void> async_setCallback(CallbackRPC.IFace cb, float firstValue)  throws MoraException {
				final int methodIndex = 22;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("SetCallback_Callback_FLOAT_");
				RemoteCall_SetCallback_Callback_FLOAT_ call = new RemoteCall_SetCallback_Callback_FLOAT_(getCommunicator(), mRemoteMethods[methodIndex], cb,  firstValue );
				return call.invoke();
			}
	
			@Override
			public void setCallback(CallbackRPC.IFace cb, float firstValue){
				try{
					CompletableFuture<Void> future = async_setCallback(cb,firstValue);
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					future.get();
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_GetCallback extends RemoteMethodCall<CallbackRPC.IFace> {
	
				public RemoteCall_GetCallback(Communicator c, RemoteMethod rm) {
					super(c, rm);
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
				}
	
				@Override
				protected CallbackRPC.IFace decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					final CallbackRPC.IFace result = CallbackRPC.read(is, communicator);
					return result;
				}
				
			}
					
			public CompletableFuture<CallbackRPC.IFace> async_getCallback()  throws MoraException {
				final int methodIndex = 23;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("GetCallback");
				RemoteCall_GetCallback call = new RemoteCall_GetCallback(getCommunicator(), mRemoteMethods[methodIndex]);
				return call.invoke();
			}
	
			@Override
			public CallbackRPC.IFace getCallback(){
				try{
					CompletableFuture<CallbackRPC.IFace> future = async_getCallback();
					future.exceptionally(f -> {
						throw new RuntimeException(f);
					});
					final CallbackRPC.IFace res = future.get();
					return res;
				} catch (InterruptedException | ExecutionException | MoraException e) {
					throw new RuntimeException(e);
				}
			}
	
			private static class RemoteCall_ThrowUnknownException extends RemoteMethodCall<Void> {
	
				public RemoteCall_ThrowUnknownException(Communicator c, RemoteMethod rm) {
					super(c, rm);
				}
	
				@Override
				protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException {
				}
	
				@Override
				protected Void decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
					return null;
				}
				
			}
					
			public CompletableFuture<Void> async_throwUnknownException()  throws MoraException {
				final int methodIndex = 24;
				if (mRemoteMethods[methodIndex] == null)
					mRemoteMethods[methodIndex] = createRemoteMethod("ThrowUnknownException");
				RemoteCall_ThrowUnknownException call = new RemoteCall_ThrowUnknownException(getCommunicator(), mRemoteMethods[methodIndex]);
				return call.invoke();
			}
	
			@Override
			public void throwUnknownException(){
				try{
					CompletableFuture<Void> future = async_throwUnknownException();
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
			
			sMethods.put("Echo_BOOL_", new Echo_BOOL_Invoker());
			sMethods.put("Echo_BYTE_", new Echo_BYTE_Invoker());
			sMethods.put("Echo_SHORT_", new Echo_SHORT_Invoker());
			sMethods.put("Echo_INTEGER_", new Echo_INTEGER_Invoker());
			sMethods.put("Echo_LONG_", new Echo_LONG_Invoker());
			sMethods.put("Echo_FLOAT_", new Echo_FLOAT_Invoker());
			sMethods.put("Echo_DOUBLE_", new Echo_DOUBLE_Invoker());
			sMethods.put("Echo_STRING_", new Echo_STRING_Invoker());
			sMethods.put("Echo_MyEnum_", new Echo_MyEnum_Invoker());
			sMethods.put("Echo_SimpleStruct_", new Echo_SimpleStruct_Invoker());
			sMethods.put("Echo_ListStruct_", new Echo_ListStruct_Invoker());
			sMethods.put("Echo1_BoolList_", new Echo1_BoolList_Invoker());
			sMethods.put("Echo2_ByteList_", new Echo2_ByteList_Invoker());
			sMethods.put("Echo3_ShortList_", new Echo3_ShortList_Invoker());
			sMethods.put("Echo4_IntList_", new Echo4_IntList_Invoker());
			sMethods.put("Echo5_LongList_", new Echo5_LongList_Invoker());
			sMethods.put("Echo6_FloatList_", new Echo6_FloatList_Invoker());
			sMethods.put("Echo7_DoubleList_", new Echo7_DoubleList_Invoker());
			sMethods.put("Echo8_StringList_", new Echo8_StringList_Invoker());
			sMethods.put("Echo9_MyEnumList_", new Echo9_MyEnumList_Invoker());
			sMethods.put("Echo10_SimpleStructList_", new Echo10_SimpleStructList_Invoker());
			sMethods.put("Echo11_ListListStruct_", new Echo11_ListListStruct_Invoker());
			sMethods.put("SetCallback_Callback_FLOAT_", new SetCallback_Callback_FLOAT_Invoker());
			sMethods.put("GetCallback", new GetCallbackInvoker());
			sMethods.put("ThrowUnknownException", new ThrowUnknownExceptionInvoker());
		}
		protected Adapter(Communicator communicator, IFace delegate, final String identifier) {
			super(communicator, delegate, identifier);
		}
		@Override
		protected IMethodInvokation<IFace> getInvoker(String methodName) {
			return sMethods.get(methodName);
		}
		public boolean echo(boolean value){
			return mDelegate.echo(value);
		}
		public byte echo(byte value){
			return mDelegate.echo(value);
		}
		public short echo(short value){
			return mDelegate.echo(value);
		}
		public int echo(int value){
			return mDelegate.echo(value);
		}
		public long echo(long value){
			return mDelegate.echo(value);
		}
		public float echo(float value){
			return mDelegate.echo(value);
		}
		public double echo(double value){
			return mDelegate.echo(value);
		}
		public String echo(String value){
			return mDelegate.echo(value);
		}
		public MyEnum echo(MyEnum value){
			return mDelegate.echo(value);
		}
		public SimpleStruct echo(SimpleStruct value){
			return mDelegate.echo(value);
		}
		public ListStruct echo(ListStruct value){
			return mDelegate.echo(value);
		}
		public BoolArrayList echo1(BoolArrayList value){
			return mDelegate.echo1(value);
		}
		public ByteArrayList echo2(ByteArrayList value){
			return mDelegate.echo2(value);
		}
		public ShortArrayList echo3(ShortArrayList value){
			return mDelegate.echo3(value);
		}
		public IntArrayList echo4(IntArrayList value){
			return mDelegate.echo4(value);
		}
		public LongArrayList echo5(LongArrayList value){
			return mDelegate.echo5(value);
		}
		public FloatArrayList echo6(FloatArrayList value){
			return mDelegate.echo6(value);
		}
		public DoubleArrayList echo7(DoubleArrayList value){
			return mDelegate.echo7(value);
		}
		public StringArrayList echo8(StringArrayList value){
			return mDelegate.echo8(value);
		}
		public List<MyEnum> echo9(List<MyEnum> value){
			return mDelegate.echo9(value);
		}
		public List<SimpleStruct> echo10(List<SimpleStruct> value){
			return mDelegate.echo10(value);
		}
		public List<ListStruct> echo11(List<ListStruct> value){
			return mDelegate.echo11(value);
		}
		public void setCallback(CallbackRPC.IFace cb, float firstValue){
			mDelegate.setCallback(cb, firstValue);
		}
		public CallbackRPC.IFace getCallback(){
			return mDelegate.getCallback();
		}
		public void throwUnknownException(){
			mDelegate.throwUnknownException();
		}
				
		private static class _getType_Invoker implements IMethodInvokation<IFace> {
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				os.writeString(TYPE_IDENTIFIER);
			}
		}
		private static class Echo_BOOL_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final boolean value = is.readBoolean();
				final boolean _result_ = delegate.echo(value);
				os.writeBoolean(_result_);
			}
		}
		private static class Echo_BYTE_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final byte value = is.readByte();
				final byte _result_ = delegate.echo(value);
				os.writeByte(_result_);
			}
		}
		private static class Echo_SHORT_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final short value = is.readShort();
				final short _result_ = delegate.echo(value);
				os.writeShort(_result_);
			}
		}
		private static class Echo_INTEGER_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final int value = is.readInteger();
				final int _result_ = delegate.echo(value);
				os.writeInteger(_result_);
			}
		}
		private static class Echo_LONG_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final long value = is.readLong();
				final long _result_ = delegate.echo(value);
				os.writeLong(_result_);
			}
		}
		private static class Echo_FLOAT_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final float value = is.readFloat();
				final float _result_ = delegate.echo(value);
				os.writeFloat(_result_);
			}
		}
		private static class Echo_DOUBLE_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final double value = is.readDouble();
				final double _result_ = delegate.echo(value);
				os.writeDouble(_result_);
			}
		}
		private static class Echo_STRING_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final String value = is.readString();
				final String _result_ = delegate.echo(value);
				os.writeString(_result_);
			}
		}
		private static class Echo_MyEnum_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final MyEnum value = MyEnum.read(is, communicator);
				final MyEnum _result_ = delegate.echo(value);
				MyEnum.write(_result_, os, communicator);
			}
		}
		private static class Echo_SimpleStruct_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final SimpleStruct value = SimpleStruct.read(is, communicator);
				final SimpleStruct _result_ = delegate.echo(value);
				SimpleStruct.write(_result_, os, communicator);
			}
		}
		private static class Echo_ListStruct_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final ListStruct value = ListStruct.read(is, communicator);
				final ListStruct _result_ = delegate.echo(value);
				ListStruct.write(_result_, os, communicator);
			}
		}
		private static class Echo1_BoolList_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final BoolArrayList value = is.readBooleanList();
				final BoolArrayList _result_ = delegate.echo1(value);
				os.writeBoolean(_result_);
			}
		}
		private static class Echo2_ByteList_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final ByteArrayList value = is.readByteList();
				final ByteArrayList _result_ = delegate.echo2(value);
				os.writeByte(_result_);
			}
		}
		private static class Echo3_ShortList_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final ShortArrayList value = is.readShortList();
				final ShortArrayList _result_ = delegate.echo3(value);
				os.writeShort(_result_);
			}
		}
		private static class Echo4_IntList_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final IntArrayList value = is.readIntegerList();
				final IntArrayList _result_ = delegate.echo4(value);
				os.writeInteger(_result_);
			}
		}
		private static class Echo5_LongList_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final LongArrayList value = is.readLongList();
				final LongArrayList _result_ = delegate.echo5(value);
				os.writeLong(_result_);
			}
		}
		private static class Echo6_FloatList_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final FloatArrayList value = is.readFloatList();
				final FloatArrayList _result_ = delegate.echo6(value);
				os.writeFloat(_result_);
			}
		}
		private static class Echo7_DoubleList_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final DoubleArrayList value = is.readDoubleList();
				final DoubleArrayList _result_ = delegate.echo7(value);
				os.writeDouble(_result_);
			}
		}
		private static class Echo8_StringList_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final StringArrayList value = is.readStringList();
				final StringArrayList _result_ = delegate.echo8(value);
				os.writeString(_result_);
			}
		}
		private static class Echo9_MyEnumList_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final List<MyEnum> value = MyEnum.readList(is, communicator);
				final List<MyEnum> _result_ = delegate.echo9(value);
				MyEnum.write(_result_, os, communicator);
			}
		}
		private static class Echo10_SimpleStructList_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final List<SimpleStruct> value = SimpleStruct.readList(is, communicator);
				final List<SimpleStruct> _result_ = delegate.echo10(value);
				SimpleStruct.write(_result_, os, communicator);
			}
		}
		private static class Echo11_ListListStruct_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final List<ListStruct> value = ListStruct.readList(is, communicator);
				final List<ListStruct> _result_ = delegate.echo11(value);
				ListStruct.write(_result_, os, communicator);
			}
		}
		private static class SetCallback_Callback_FLOAT_Invoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final CallbackRPC.IFace cb = CallbackRPC.read(is, communicator);
				final float firstValue = is.readFloat();
				delegate.setCallback(cb, firstValue);
			}
		}
		private static class GetCallbackInvoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				final CallbackRPC.IFace _result_ = delegate.getCallback();
				CallbackRPC.write(_result_, os, communicator);
			}
		}
		private static class ThrowUnknownExceptionInvoker implements IMethodInvokation<IFace> {
			
			@Override
			public void invoke(IFace delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException {
				delegate.throwUnknownException();
			}
		}
	}
}
