/**
 *
 */
package com.cebucouncilbsp.backend.responsedto;

/**
 * @author reneir.val.t.perez
 *
 */
public class LoginResponseDto {

	private Integer userId;
	private String authorityCode;
	private Integer institutionId;
	private String area;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
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
