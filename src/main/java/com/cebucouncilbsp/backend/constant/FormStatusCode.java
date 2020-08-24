/**
 *
 */
package com.cebucouncilbsp.backend.constant;

/**
 * @author reneir.val.t.perez
 *
 */
public enum FormStatusCode {

	SUBMITTED("00"), PAID("01"), PROCESSED("02");

	private final String code;

	FormStatusCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
