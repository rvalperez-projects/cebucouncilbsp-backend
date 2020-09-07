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
public enum ISComPositionCategoryCode {

	ISCOM_REP("00"), PARENT_REP("01"), ISC_CHAIR("02"), ISC_COORDINATOR("03"), UNIT_LEADER("04"),
	ASST_UNIT_LEADER("05");

	private final String code;

	private static Map<String, ISComPositionCategoryCode> map = new HashMap<>();

	static {
		for (ISComPositionCategoryCode value : values()) {
			map.put(value.getCode(), value);
		}
		map = Collections.unmodifiableMap(map);
	}

	ISComPositionCategoryCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static ISComPositionCategoryCode get(String code) {
		return map.get(code);
	}
}
