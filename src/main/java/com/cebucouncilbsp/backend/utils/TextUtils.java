/**
 *
 */
package com.cebucouncilbsp.backend.utils;

import org.springframework.util.StringUtils;

/**
 * @author reneir.val.t.perez
 *
 */
public class TextUtils {

	private TextUtils() {
	}

	public static String getFullName(String surname, String givenName, String middleInitial) {
		if (StringUtils.hasText(middleInitial)) {
			return String.format("%s %s. %s", givenName, middleInitial, surname);
		} else {
			return String.format("%s %s", givenName, surname);
		}
	}

}
