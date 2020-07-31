/**
 *
 */
package com.cebucouncilbsp.backend.constant;

/**
 * @author reneir.val.t.perez
 *
 */
public enum AuthorityCategoryCode {

	GENERAL_USER("00"), COUNCIL("01"), ADMIN("02");

	private final String code;

	AuthorityCategoryCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
