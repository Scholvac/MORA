package de.sos.mora.com;

import java.io.IOException;

import de.sos.mora.IMoraAdapter;
import de.sos.mora.com.MoraMessages.InMethodCall;
import de.sos.mora.com.MoraMessages.OutMethodRespond;
import de.sos.mora.exceptions.MoraAdapterException;
import de.sos.mora.exceptions.MoraException;

public class MessageHandler implements Runnable {

	private final Communicator 		mCommunicator;
	private final InMethodCall 		mMessage;

	public MessageHandler(Communicator communicator, final InMethodCall inMoraMsg) {
		mCommunicator = communicator;
		mMessage = inMoraMsg;
	}

	public void run() {
		try {
			handle(mMessage);
		}catch(Throwable t) {
			sendException(t);
		}
	}

	private void sendException(Throwable t) {
		System.out.println("Got an Error: " + t.getMessage());
		t.printStackTrace();
	}

	private void handle(InMethodCall inMsg) throws MoraException, IOException {
		IMoraAdapter<?> adapter = mCommunicator.getAdapter(mMessage.objectIdentifier);
		if (adapter == null)
			throw new MoraAdapterException("Could not find adapter with identifier: " + mMessage.objectIdentifier);
		
		OutMethodRespond response = null;
		try{
			response = new OutMethodRespond(inMsg);
			adapter.invoke(inMsg, response, mCommunicator);
		}catch(Throwable t) {
			try {
				response = new OutMethodRespond(inMsg, t);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		assert(response != null);
		
		mCommunicator.sendResponse(response);		
	}

}
