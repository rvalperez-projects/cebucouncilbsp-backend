/**
 *
 */
package com.cebucouncilbsp.backend.requestdto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
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
	@Size(max = 7)
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
	@NotNull
	private List<@Valid UnitRegistrationISComRequestForm> iscomMembersList;
	@NotNull
	private List<@Valid UnitRegistrationMemberRequestForm> unitMembersList;
	private String officialReceiptNo;
	private LocalDate officialReceiptDate;
	private LocalDate expirationDate;
	private LocalDateTime dateApplied;
	private String councilRegistrationOfficer;
	private LocalDateTime councilProcessedDate;
	private String councilScoutExecutive;
	private LocalDate councilApprovedDate;
	private String regionalRegistrationOfficer;
	private LocalDateTime regionalProcessedDate;
	private String regionalScoutExecutive;
	private LocalDate regionalApprovedDate;

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

	public List<UnitRegistrationISComRequestForm> getIscomMembersList() {
		return iscomMembersList;
	}

	public void setIscomMembersList(List<UnitRegistrationISComRequestForm> iscomMembersList) {
		this.iscomMembersList = iscomMembersList;
	}

	public List<UnitRegistrationMemberRequestForm> getUnitMembersList() {
		return unitMembersList;
	}

	public void setUnitMembersList(List<UnitRegistrationMemberRequestForm> unitMembersList) {
		this.unitMembersList = unitMembersList;
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

	public String getCouncilRegistrationOfficer() {
		return councilRegistrationOfficer;
	}

	public void setCouncilRegistrationOfficer(String councilRegistrationOfficer) {
		this.councilRegistrationOfficer = councilRegistrationOfficer;
	}

	public LocalDateTime getCouncilProcessedDate() {
		return councilProcessedDate;
	}

	public void setCouncilProcessedDate(LocalDateTime councilProcessedDate) {
		this.councilProcessedDate = councilProcessedDate;
	}

	public String getCouncilScoutExecutive() {
		return councilScoutExecutive;
	}

	public void setCouncilScoutExecutive(String councilScoutExecutive) {
		this.councilScoutExecutive = councilScoutExecutive;
	}

	public LocalDate getCouncilApprovedDate() {
		return councilApprovedDate;
	}

	public void setCouncilApprovedDate(LocalDate councilApprovedDate) {
		this.councilApprovedDate = councilApprovedDate;
	}

	public String getRegionalRegistrationOfficer() {
		return regionalRegistrationOfficer;
	}

	public void setRegionalRegistrationOfficer(String regionalRegistrationOfficer) {
		this.regionalRegistrationOfficer = regionalRegistrationOfficer;
	}

	public LocalDateTime getRegionalProcessedDate() {
		return regionalProcessedDate;
	}

	public void setRegionalProcessedDate(LocalDateTime regionalProcessedDate) {
		this.regionalProcessedDate = regionalProcessedDate;
	}

	public String getRegionalScoutExecutive() {
		return regionalScoutExecutive;
	}

	public void setRegionalScoutExecutive(String regionalScoutExecutive) {
		this.regionalScoutExecutive = regionalScoutExecutive;
	}

	public LocalDate getRegionalApprovedDate() {
		return regionalApprovedDate;
	}

	public void setRegionalApprovedDate(LocalDate regionalApprovedDate) {
		this.regionalApprovedDate = regionalApprovedDate;
	}

	@Override
	public String toString() {
		return "UnitRegistrationFormRequestForm [formId=" + formId + ", unitRegistrationNo=" + unitRegistrationNo
				+ ", unitNumber=" + unitNumber + ", sectionCode=" + sectionCode + ", charterFlag=" + charterFlag
				+ ", statusCode=" + statusCode + ", institutionId=" + institutionId + ", iscomMembersList="
				+ iscomMembersList + ", unitMembersList=" + unitMembersList + ", officialReceiptNo=" + officialReceiptNo
				+ ", officialReceiptDate=" + officialReceiptDate + ", expirationDate=" + expirationDate
				+ ", dateApplied=" + dateApplied + ", councilRegistrationOfficer=" + councilRegistrationOfficer
				+ ", councilProcessedDate=" + councilProcessedDate + ", councilScoutExecutive=" + councilScoutExecutive
				+ ", councilApprovedDate=" + councilApprovedDate + ", regionalRegistrationOfficer="
				+ regionalRegistrationOfficer + ", regionalProcessedDate=" + regionalProcessedDate
				+ ", regionalScoutExecutive=" + regionalScoutExecutive + ", regionalApprovedDate="
				+ regionalApprovedDate + "]";
	}

}
