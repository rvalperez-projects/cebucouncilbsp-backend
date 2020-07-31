package com.cebucouncilbsp.backend;

import java.util.List;

public class ResponseBodyWrapper {
	private String status;
	private Object result;
	private List<String> errorMessages;

	/**
	 * ResponseBodyWrapper
	 *
	 * @param status        object
	 * @param result        object
	 * @param errorMessages object
	 */
	public ResponseBodyWrapper(String status, Object result, List<String> errorMessages) {
		super();
		this.status = status;
		this.result = result;
		this.errorMessages = errorMessages;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
}
