/**
 *
 */
package com.cebucouncilbsp.backend.entity;

/**
 * @author reneir.val.t.perez
 *
 */
public class UserEntity extends BaseEntity {

	private Integer userId;
	private String surname;
	private String givenName;
	private String middleInitial;
	private String emailAddress;
	private String mobileNumber;
	private Integer institutionId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Integer getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}
}
