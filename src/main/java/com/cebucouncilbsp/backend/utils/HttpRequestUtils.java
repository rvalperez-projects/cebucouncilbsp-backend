/**
 *
 */
package com.cebucouncilbsp.backend.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author reneir.val.t.perez
 *
 */
public class HttpRequestUtils {

	private static final String AUTH_HEADER = "Authorization";
	private static final String AUTH_HEADER_TYPE = "Bearer";

	private HttpRequestUtils() {
	}

	/**
	 * Extract authentication token from Authorization Header.
	 *
	 * @param request HttpServletRequest
	 * @return Authentication Token
	 */
	public static String getAuthToken(HttpServletRequest request) {
		String authToken = null;
		final String authHeader = request.getHeader(AUTH_HEADER);
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			authToken = authHeader.substring(AUTH_HEADER_TYPE.length()).trim();
		}
		return authToken;
	}
}
