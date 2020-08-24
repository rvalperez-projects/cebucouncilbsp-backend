/**
 *
 */
package com.cebucouncilbsp.backend.entity;

/**
 * @author reneir.val.t.perez
 *
 */
public class AuthorityEntity extends BaseEntity {

	private Integer userId;
	private String token;
	private String username;
	private String password;
	private String roleCode;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}
