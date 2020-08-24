/**
 *
 */
package com.cebucouncilbsp.backend.constant;

/**
 * @author reneir.val.t.perez
 *
 */
public enum HighestTrainingCode {

	ORIENTATION("00"), BTC("01"), ATC("02"), CML("03"), CMT("04"), OTHERS("05");

	private final String code;

	HighestTrainingCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
