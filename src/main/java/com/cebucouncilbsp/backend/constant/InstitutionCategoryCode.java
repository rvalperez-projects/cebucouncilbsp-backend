/**
 *
 */
package com.cebucouncilbsp.backend.constant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author reneir.val.t.perez
 *
 */
public enum InstitutionCategoryCode {

	PRESCHOOL("00"), PRIMARY("01"), SECONDARY("02"), SENIOR_HIGH("03"), COLLEGE("04"), COMMUNITY("05");

	private final String code;
	private static Map<String, InstitutionCategoryCode> map = new HashMap<>();

	static {
		for (InstitutionCategoryCode value : values()) {
			map.put(value.getCode(), value);
		}
		map = Collections.unmodifiableMap(map);
	}

	InstitutionCategoryCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static InstitutionCategoryCode get(String code) {
		return map.get(code);
	}
}
