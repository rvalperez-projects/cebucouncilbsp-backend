/**
 *
 */
package com.cebucouncilbsp.backend.constant;

/**
 * @author reneir.val.t.perez
 *
 */
public enum InstitutionCategoryCode {

	PRESCHOOL("00"), PRIMARY("01"), SECONDARY("02"), SENIOR_HIGH("03"), COLLEGE("04"), COMMUNITY("05");

	private final String code;

	InstitutionCategoryCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
