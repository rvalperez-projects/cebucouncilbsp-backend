/**
 *
 */
package com.cebucouncilbsp.backend.requestdto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author reneir.val.t.perez
 *
 */
public class UserSignUpRequestForm {

	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String authorityCode;
	@NotBlank
	private String surname;
	@NotBlank
	private String givenName;
	private String middleInitial;
//	@Pattern(regexp = "09[0-9]{2}-[0-9]{3}-[0-9]{4}", message = "InvalidMobileNumberFormat")
	private String mobileNumber;
	@NotBlank
	@Email
	private String emailAddress;
	@NotBlank
	private String institutionName;
	@NotBlank
	private String address;
	@NotBlank
	private String district;
	@NotBlank
	private String area;
	private String contactNumber;
	@NotBlank
	private String categoryCode;

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

	public String getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Override
	public String toString() {
		return "UserSignUpRequestForm [username=" + username + ", password=" + password + ", authorityCode="
				+ authorityCode + ", surname=" + surname + ", givenName=" + givenName + ", middleInitial="
				+ middleInitial + ", mobileNumber=" + mobileNumber + ", emailAddress=" + emailAddress
				+ ", institutionName=" + institutionName + ", address=" + address + ", district=" + district + ", area="
				+ area + ", contactNumber=" + contactNumber + ", categoryCode=" + categoryCode + "]";
	}
}
