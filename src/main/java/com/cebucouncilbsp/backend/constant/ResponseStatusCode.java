/**
 *
 */
package com.cebucouncilbsp.backend.constant;

/**
 * @author reneir.val.t.perez
 *
 */
public enum ResponseStatusCode {

	SUCCESS("SUCCESS"), VALIDATION_FAILURE("VALIDATION_FAILURE"), SYSTEM_ERROR("SYSTEM_ERROR");

	private final String code;

	ResponseStatusCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
