/**
 *
 */
package com.cebucouncilbsp.backend.entity;

/**
 * @author reneir.val.t.perez
 *
 */
public class MemberDetailsEntity extends BaseEntity {

	private Integer memberId;
	private Integer formId;
	private String positionCode;
	private String surname;
	private String givenName;
	private String middleInitial;
	private String registrationStatusCode;
	private Integer age;
	private String membershipCertNo;
	private String highestBadgeCode;
	private Integer tenure;
	private String religion;

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
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

	public String getRegistrationStatusCode() {
		return registrationStatusCode;
	}

	public void setRegistrationStatusCode(String registrationStatusCode) {
		this.registrationStatusCode = registrationStatusCode;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMembershipCertNo() {
		return membershipCertNo;
	}

	public void setMembershipCertNo(String membershipCertNo) {
		this.membershipCertNo = membershipCertNo;
	}

	public String getHighestBadgeCode() {
		return highestBadgeCode;
	}

	public void setHighestBadgeCode(String highestBadgeCode) {
		this.highestBadgeCode = highestBadgeCode;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}
}
