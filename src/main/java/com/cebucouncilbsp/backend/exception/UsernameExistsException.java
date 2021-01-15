package com.cebucouncilbsp.backend.exception;

@SuppressWarnings("serial")
public class UsernameExistsException extends RuntimeException {

	public UsernameExistsException() {
		super();
	}

	public UsernameExistsException(String message) {
		super(message);
	}

	public UsernameExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameExistsException(Throwable cause) {
		super(cause);
	}

	protected UsernameExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
