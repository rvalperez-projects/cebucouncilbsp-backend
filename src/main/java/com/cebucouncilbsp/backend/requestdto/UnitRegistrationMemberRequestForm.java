/**
 *
 */
package com.cebucouncilbsp.backend.requestdto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author reneir.val.t.perez
 *
 */
public class UnitRegistrationMemberRequestForm {

	private Integer memberId;
	private Integer formId;
	@NotNull
	private String positionCode;
	@NotNull
	private String surname;
	@NotNull
	private String givenName;
	@Size(max = 1)
	private String middleInitial;
	@NotNull
	@Size(max = 2)
	private String registrationStatusCode;
	@NotNull
	private Integer age;
	private String membershipCertNo;
	@Size(max = 2)
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

	@Override
	public String toString() {
		return "UnitRegistrationMemberRequestForm [memberId=" + memberId + ", formId=" + formId + ", positionCode="
				+ positionCode + ", surname=" + surname + ", givenName=" + givenName + ", middleInitial="
				+ middleInitial + ", registrationStatusCode=" + registrationStatusCode + ", age=" + age
				+ ", membershipCertNo=" + membershipCertNo + ", highestBadgeCode=" + highestBadgeCode + ", tenure="
				+ tenure + ", religion=" + religion + "]";
	}
}
