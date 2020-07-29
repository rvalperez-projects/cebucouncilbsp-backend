/**
 *
 */
package com.cebucouncilbsp.backend.requestdto;

import org.springframework.lang.NonNull;

/**
 * @author reneir.val.t.perez
 *
 */
public class LoginRequestForm {

	@NonNull
	private String username;

	@NonNull
	private String password;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
