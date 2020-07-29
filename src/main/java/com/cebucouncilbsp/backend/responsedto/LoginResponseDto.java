/**
 *
 */
package com.cebucouncilbsp.backend.responsedto;

import java.math.BigInteger;

/**
 * @author reneir.val.t.perez
 *
 */
public class LoginResponseDto {

	private BigInteger userId;
	private String authorityCode;
	private BigInteger institutionalId;
	private String area;

	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public String getAuthorityCode() {
		return authorityCode;
	}
	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}
	public BigInteger getInstitutionalId() {
		return institutionalId;
	}
	public void setInstitutionalId(BigInteger institutionalId) {
		this.institutionalId = institutionalId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
}
