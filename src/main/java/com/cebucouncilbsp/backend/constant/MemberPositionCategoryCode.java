/**
 *
 */
package com.cebucouncilbsp.backend.constant;

/**
 * @author reneir.val.t.perez
 *
 */
public enum MemberPositionCategoryCode {

	SPL("00"), SCL("01"), RL("02"), ARL1("03"), ARL2("04"), AUDITOR("05"), SCRIBE("06"), TREASURER("07"),
	QUARTERMASTER("08"), MEMBER("09");

	private final String code;

	MemberPositionCategoryCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
