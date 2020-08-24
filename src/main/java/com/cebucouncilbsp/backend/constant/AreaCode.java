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
public enum AreaCode {

	I("1"), II("2"), III("3"), IV("4"), V("5"), VI("6"), VII("7"), VIII("8"), IX("9"), X("10"), XI("11"), XII("12");

	private final String code;

	private static Map<String, AreaCode> map = new HashMap<>();

	static {
		for (AreaCode value : values()) {
			map.put(value.getCode(), value);
		}
		map = Collections.unmodifiableMap(map);
	}

	AreaCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static AreaCode get(String code) {
		return map.get(code);
	}
}
