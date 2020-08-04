package com.cebucouncilbsp.backend.exception;

@SuppressWarnings("serial")
public class SystemFailureException extends RuntimeException {
	public SystemFailureException() {
		super();
	}

	public SystemFailureException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SystemFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemFailureException(String message) {
		super(message);
	}

	public SystemFailureException(Throwable cause) {
		super(cause);
	}
}
