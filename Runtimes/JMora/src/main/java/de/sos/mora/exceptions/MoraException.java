package de.sos.mora.exceptions;

public class MoraException extends Exception {

	public MoraException() {
		// TODO Auto-generated constructor stub
	}

	public MoraException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public MoraException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public MoraException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MoraException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public String getQualifiedException() {
		return getClass().getName() + ":" + getMessage();
	}

	public void setMessage(String msg) {
		System.out.println("TODO: Set message: \n\n\t" + msg);
	}

}
