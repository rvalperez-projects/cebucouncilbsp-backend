/**
 *
 */
package com.cebucouncilbsp.backend.requestdto;

import org.springframework.lang.NonNull;

/**
 * @author reneir.val.t.perez
 *
 */
public class LogoutRequestForm {

	@NonNull
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
