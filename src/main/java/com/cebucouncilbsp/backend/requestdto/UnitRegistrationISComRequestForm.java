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
public class UnitRegistrationISComRequestForm {

	private Integer iscomId;
	private Integer formId;
	@NotNull
	private String positionCode;
	@NotNull
	private String surname;
	@NotNull
	private String givenName;
	@Size(max = 1)
	private String middleInitial;
	private String signature;
	@NotNull
	private Integer age;
	private String membershipCertNo;
	@Size(max = 2)
	private String highestTrainingCode;
	private Integer tenure;
	private String religion;

	public Integer getIscomId() {
		return iscomId;
	}

	public void setIscomId(Integer setIscomId) {
		this.iscomId = setIscomId;
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

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
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

	public String getHighestTrainingCode() {
		return highestTrainingCode;
	}

	public void setHighestTrainingCode(String highestTrainingCode) {
		this.highestTrainingCode = highestTrainingCode;
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
		return "UnitRegistrationISComRequestForm [iscomId=" + iscomId + ", formId=" + formId + ", positionCode="
				+ positionCode + ", surname=" + surname + ", givenName=" + givenName + ", middleInitial="
				+ middleInitial + ", signature=" + signature + ", age=" + age + ", membershipCertNo=" + membershipCertNo
				+ ", highestTrainingCode=" + highestTrainingCode + ", tenure=" + tenure + ", religion=" + religion
				+ "]";
	}
}
