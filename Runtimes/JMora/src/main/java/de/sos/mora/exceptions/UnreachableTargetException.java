package de.sos.mora.exceptions;

import de.sos.mora.RemoteObjects.RemoteCommunicator;

public class UnreachableTargetException extends MoraException {

	private final RemoteCommunicator mTarget;

	public UnreachableTargetException(RemoteCommunicator target, Throwable cause) {
		super("Target: " + target + " is unreachable", cause);
		mTarget = target;
	}

}
