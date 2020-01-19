package de.sos.mora.com;

import de.sos.mora.RemoteObjects.RemoteCommunicator;

public interface IConnectionLostHandler {

	public void onConnectionLost(final RemoteCommunicator target);
}
