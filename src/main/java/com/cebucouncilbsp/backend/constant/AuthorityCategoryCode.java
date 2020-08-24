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
public enum AuthorityCategoryCode {

	GENERAL_USER("00", "USER"), COUNCIL("01", "COUNCIL"), ADMIN("99", "ADMIN");

	private final String code;
	private final String role;

	private static Map<String, AuthorityCategoryCode> map = new HashMap<>();

	static {
		for (AuthorityCategoryCode value : values()) {
			map.put(value.getCode(), value);
		}
		map = Collections.unmodifiableMap(map);
	}

	AuthorityCategoryCode(String code, String role) {
		this.code = code;
		this.role = role;
	}

	public String getCode() {
		return code;
	}

	public String getRole() {
		return role;
	}

	public static AuthorityCategoryCode get(String code) {
		return map.get(code);
	}
}
