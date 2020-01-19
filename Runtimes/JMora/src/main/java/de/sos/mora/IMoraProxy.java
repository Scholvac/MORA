package de.sos.mora;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import de.sos.mora.RemoteObjects.RemoteMethod;
import de.sos.mora.RemoteObjects.RemoteObject;
import de.sos.mora.com.Communicator;
import de.sos.mora.exceptions.MoraException;
import de.sos.mora.exceptions.MoraInvalidAddressException;
import de.sos.mora.stream.IMoraInputStream;
import de.sos.mora.stream.IMoraOutputStream;

public abstract class IMoraProxy<T> {
	
	
//	public static class ResultValue<T> {
//		public final T value;
//		public final MoraException errorMessage;
//		public ResultValue(final T v, final MoraException error) {
//			value = v; 
//			errorMessage = error;
//		}
//		public boolean isError() {
//			return errorMessage != null;
//		}
//	}
//	
	
	
	
	
	
	
	
	
	final RemoteObject		mRemoteObject;
	final Communicator		mCommunicator;
	
	
	public IMoraProxy(Communicator communicator, RemoteObject remoteObject) throws MoraInvalidAddressException {
		mCommunicator = communicator;
		mRemoteObject = remoteObject;
	}
	
	public Communicator getCommunicator() { return mCommunicator; }
	public RemoteObject getRemoteObject() { return mRemoteObject; }

	public String getQualifiedIdentifier() {
		return mRemoteObject.getIdentifier();
	}
	

//	/** Helper method to generate a valid call - object, to be filled with parameters by the caller */
//	protected OutMethodCall createCall(String methodSig) throws MoraException {
//		return mCommunicator.createCall(mRemoteObject, methodSig);
//	}
//	/** Helper method to generate a valid call - object, to be filled with parameters by the caller */
//	protected OutMethodCall createCall(final RemoteMethod remoteMethod) throws MoraException {
//		return mCommunicator.createCall(remoteMethod);
//	}
	
	public RemoteMethod createRemoteMethod(String signature) {
		return new RemoteMethod(mRemoteObject, signature);
	}
	
	
	static class GetType_Async extends RemoteMethodCall<String> {	
		protected GetType_Async(Communicator c, RemoteMethod rm) {
			super(c, rm);
		}
		@Override
		protected void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException { } //nothing to do
		@Override
		protected String decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException {
			return is.readString();
		}
	}
	private RemoteMethod mRemoteGetType = null;
	
	public boolean checkType() {
		try {
			if (mRemoteGetType == null)
				mRemoteGetType = createRemoteMethod("_getType_");
			GetType_Async remoteCall = new GetType_Async(mCommunicator, mRemoteGetType);
			CompletableFuture<String> future = remoteCall.invoke();
			String type = future.get(mCommunicator.getTimeout(), mCommunicator.getTimeoutUnit());	
			return type != null && type.equals(getTypeIdentifier());
		} catch (InterruptedException | ExecutionException | TimeoutException | MoraException e) {
//			e.printStackTrace();
			//TODO: debug log message
		}
		return false;
	}


	public abstract String getTypeIdentifier();



	
}
