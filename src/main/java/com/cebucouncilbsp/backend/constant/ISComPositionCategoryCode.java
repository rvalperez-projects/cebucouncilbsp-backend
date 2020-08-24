/**
 *
 */
package com.cebucouncilbsp.backend.constant;

/**
 * @author reneir.val.t.perez
 *
 */
public enum ISComPositionCategoryCode {

	ISCOM_REP("00"), PARENT_REP("01"), ISC_CHAIR("02"), ISC_COORDINATOR("03"), UNIT_LEADER("04"),
	ASST_UNIT_LEADER("05");

	private final String code;

	ISComPositionCategoryCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
