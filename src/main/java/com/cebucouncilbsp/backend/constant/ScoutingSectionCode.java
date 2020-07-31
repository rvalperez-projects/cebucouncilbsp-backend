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
public enum ScoutingSectionCode {

	LANGKAY("00", "Langkay"), KAWAN("01", "Kawan"), TROOP("02", "Troop"), OUTFIT("03", "Outfit"),
	CIRCLE("04", "Circle");

	private final String code;
	private final String sectionName;
	private static Map<String, ScoutingSectionCode> map = new HashMap<>();

	static {
		for (ScoutingSectionCode value : values()) {
			map.put(value.getCode(), value);
		}
		map = Collections.unmodifiableMap(map);
	}

	ScoutingSectionCode(String code, String sectionName) {
		this.code = code;
		this.sectionName = sectionName;
	}

	public String getCode() {
		return code;
	}

	public String getSectionName() {
		return sectionName;
	}

	public static ScoutingSectionCode get(String code) {
		return map.get(code);
	}
}
