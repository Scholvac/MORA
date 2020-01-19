package de.sos.mora.com;

import de.sos.mora.RemoteObjects.RemoteCommunicator;

public interface ISocketServer {

	
	public void start(Communicator communicator);
	public void waitForTermination() throws InterruptedException;
	public RemoteCommunicator getResponseAddress();
	public void stop() throws InterruptedException;
}
