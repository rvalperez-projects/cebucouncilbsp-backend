/**
 *
 */
package com.cebucouncilbsp.backend.constant;

/**
 * @author reneir.val.t.perez
 *
 */
public enum RegistrationStatusCode {

	NEW("N"), RE_REGISTERING("RR");

	private final String code;

	RegistrationStatusCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
