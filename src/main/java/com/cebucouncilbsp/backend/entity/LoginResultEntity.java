/**
 *
 */
package com.cebucouncilbsp.backend.entity;

/**
 * @author reneir.val.t.perez
 *
 */
public class LoginResultEntity {

	private Integer userId;
	private String roleCode;
	private Integer institutionId;
	private String area;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Integer getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}
