package com.cebucouncilbsp.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AccessForbiddenException extends RuntimeException {
	private final transient Errors errors;

	public AccessForbiddenException() {
		super();
		this.errors = null;
	}

	public AccessForbiddenException(String message) {
		super(message);
		this.errors = null;
	}

	public AccessForbiddenException(Throwable cause) {
		super(cause);
		this.errors = null;
	}

	public AccessForbiddenException(String message, Throwable cause) {
		super(message, cause);
		this.errors = null;
	}

	public AccessForbiddenException(Errors errors) {
		super();
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}
}
