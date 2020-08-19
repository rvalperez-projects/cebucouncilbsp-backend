/**
 *
 */
package com.cebucouncilbsp.backend.requestdto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author reneir.val.t.perez
 *
 */
public class UnitRegistrationFormRequestForm {

	private Integer formId;
	@Size(max = 10)
	private String unitRegistrationNo;
	@Size(max = 6)
	private String unitNumber;
	@NotNull
	@Size(max = 2)
	private String sectionCode;
	@NotNull
	private Boolean charterFlag;
	@NotNull
	@Size(max = 2)
	private String statusCode;
	@NotNull
	private Integer institutionId;
	@NotEmpty
	private List<@Valid UnitRegistrationISComRequestForm> institutionalCommitteeList;
	@NotEmpty
	private List<@Valid UnitRegistrationMemberRequestForm> patrolMembersList;
	private String officialReceiptNo;
	private LocalDate officialReceiptDate;
	private LocalDate expirationDate;
	private LocalDateTime dateApplied;

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public String getUnitRegistrationNo() {
		return unitRegistrationNo;
	}

	public void setUnitRegistrationNo(String unitRegistrationNo) {
		this.unitRegistrationNo = unitRegistrationNo;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public Boolean getCharterFlag() {
		return charterFlag;
	}

	public void setCharterFlag(Boolean charterFlag) {
		this.charterFlag = charterFlag;
	}

	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}

	public List<UnitRegistrationISComRequestForm> getInstitutionalCommitteeList() {
		return institutionalCommitteeList;
	}

	public void setInstitutionalCommitteeList(List<UnitRegistrationISComRequestForm> institutionalCommitteeList) {
		this.institutionalCommitteeList = institutionalCommitteeList;
	}

	public List<UnitRegistrationMemberRequestForm> getPatrolMembersList() {
		return patrolMembersList;
	}

	public void setPatrolMembersList(List<UnitRegistrationMemberRequestForm> patrolMembersList) {
		this.patrolMembersList = patrolMembersList;
	}

	public String getOfficialReceiptNo() {
		return officialReceiptNo;
	}

	public void setOfficialReceiptNo(String officialReceiptNo) {
		this.officialReceiptNo = officialReceiptNo;
	}

	public LocalDate getOfficialReceiptDate() {
		return officialReceiptDate;
	}

	public void setOfficialReceiptDate(LocalDate officialReceiptDate) {
		this.officialReceiptDate = officialReceiptDate;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public LocalDateTime getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(LocalDateTime dateApplied) {
		this.dateApplied = dateApplied;
	}
}
