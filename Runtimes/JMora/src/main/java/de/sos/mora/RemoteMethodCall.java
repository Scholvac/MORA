package de.sos.mora;

import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.CompletableFuture;

import de.sos.mora.RemoteObjects.RemoteMethod;
import de.sos.mora.com.Communicator;
import de.sos.mora.com.MoraMessages.InMethodRespond;
import de.sos.mora.com.MoraMessages.OutMethodCall;
import de.sos.mora.exceptions.MoraException;
import de.sos.mora.stream.IMoraInputStream;
import de.sos.mora.stream.IMoraOutputStream;

public abstract class RemoteMethodCall<T> extends CompletableFuture<T> {
	private final short mMagic;
	private final Communicator mCommunicator;
	private final RemoteMethod mRemoteMethod;

	private OutMethodCall mOutMessage;

	protected RemoteMethodCall(final Communicator c, final RemoteMethod rm) {
		mMagic = Common.generateShortMagic();

		mCommunicator = c;
		mRemoteMethod = rm;
	}

	public CompletableFuture<T> invoke() throws MoraException {
		try {
			mOutMessage = mCommunicator.createCall(mRemoteMethod, mMagic);
			assert (mOutMessage != null);

			encodeParameters(mOutMessage.stream, mCommunicator);

			mCommunicator.call(this);

			return this;
		} catch(SocketException se) {
			throw new MoraException("Could not reach target communicator", se);
		}catch (Throwable e) {
			throw new MoraException(e);
		}
	}

	public void handleResponse(final InMethodRespond resp) {
		if (resp.isError())
			this.completeExceptionally(resp.exception);
		else {
			// decode result
			try {
				this.complete(decodeReturnValue(resp.getStream(), mCommunicator));
			} catch (IOException e) {
				e.printStackTrace();
				this.completeExceptionally(e);
			}
			// end of decoding
		}
	}

	protected abstract void encodeParameters(IMoraOutputStream os, Communicator communicator) throws IOException;

	protected abstract T decodeReturnValue(IMoraInputStream is, Communicator communicator) throws IOException;

	public OutMethodCall getMessage() {
		return mOutMessage;
	}

	public short getMagic() {
		return mMagic;
	}
}