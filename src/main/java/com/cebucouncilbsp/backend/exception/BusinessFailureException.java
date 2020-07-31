package com.cebucouncilbsp.backend.exception;

import org.springframework.validation.Errors;

@SuppressWarnings("serial")
public class BusinessFailureException extends RuntimeException {
	private final transient Errors errors;
	private final transient Object[] args;

	/**
	 * Default Constructor
	 */
	public BusinessFailureException() {
		super();
		this.errors = null;
		this.args = null;
	}

	/**
	 * Set the message and instantiate.
	 *
	 * @param message Message
	 */
	public BusinessFailureException(String message) {
		super(message);
		this.errors = null;
		this.args = null;
	}

	/**
	 * Set Bean Validation results and instantiate.
	 *
	 * @param errors Validation result
	 */
	public BusinessFailureException(Errors errors) {
		super();
		this.errors = errors;
		this.args = null;
	}

	/**
	 * Set Bean Validation results and instantiate.
	 *
	 * @param errors Validation result
	 * @param args   Object arguments
	 */
	public BusinessFailureException(Errors errors, Object[] args) {
		super();
		this.errors = errors;
		this.args = args;
	}

	public Errors getErrors() {
		return errors;
	}

	public Object[] getArgs() {
		return args;
	}

}
