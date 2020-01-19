package de.sos.mora;

import java.io.IOException;

import de.sos.mora.com.Communicator;
import de.sos.mora.com.MoraMessages.InMethodCall;
import de.sos.mora.com.MoraMessages.OutMethodRespond;
import de.sos.mora.exceptions.MoraAdapterException;
import de.sos.mora.stream.IMoraInputStream;
import de.sos.mora.stream.IMoraOutputStream;

public abstract class IMoraAdapter<T> {
	
//	public static class ResultMessage<T> {
//		public final T 						value;
//		public final IMoraTypeEncoder<?>	encoder;
//		public final MoraException			throwable;
//		
//		public ResultMessage(final T v, IMoraTypeEncoder<?> e, final Throwable t) {
//			this.value = v;
//			this.encoder = e;
//			if (t != null) {
//				if (t instanceof MoraException)
//					this.throwable = (MoraException)t;
//				else
//					this.throwable = new MoraException(t);
//			}else
//				this.throwable = null;
//		}
//	}
	
	public interface IMethodInvokation<T> {
		public void invoke(T delegate, IMoraInputStream is, IMoraOutputStream os, Communicator communicator) throws IOException;
	}
		
	private final Communicator							mCommunicator;
	private final String		 						mIdentifier;
	
	protected final T 									mDelegate;
	private String										mQualifiedIdentifier;

	protected IMoraAdapter(Communicator communicator, T delegate, String identifier) {
		super();
		this.mCommunicator = communicator;
		this.mDelegate = delegate;
		this.mIdentifier = identifier;
	}
	
	public String getIdentifier() {
		return mIdentifier;
	}
	public String getQualifiedIdentifier() {
		if (mQualifiedIdentifier == null) {
			mQualifiedIdentifier = mCommunicator.getResponseAddress() + "/" + mIdentifier;
		} 
		return mQualifiedIdentifier;
	}	
	public T getDelegate() { return mDelegate; }


	public void invoke(final InMethodCall call, OutMethodRespond response, final Communicator ctx) throws MoraAdapterException {
		final IMethodInvokation<T> method = getInvoker(call.methodSignature);
		if (method == null)
			throw new MoraAdapterException("No Method with signature: " + call.methodSignature + " found in " + mIdentifier);
		try {
			method.invoke(mDelegate, call.getStream(), response.stream, ctx);
		}catch(Throwable t) {
			t.printStackTrace();
			throw new MoraAdapterException("Failure when invoking method: " + call.methodSignature + " on " + mIdentifier + " Error = " + t.getMessage(), t);
		}
	}


	/** returns an method invoker for a given signature */
	protected abstract IMethodInvokation<T> getInvoker(String signature);


	
}
