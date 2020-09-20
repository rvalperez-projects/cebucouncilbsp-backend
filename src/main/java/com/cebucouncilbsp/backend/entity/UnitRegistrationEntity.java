/**
 *
 */
package com.cebucouncilbsp.backend.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author reneir.val.t.perez
 *
 */
public class UnitRegistrationEntity extends BaseEntity {

	private Integer formId;
	private Integer institutionId;
	private String institutionName;
	private String district;
	private String unitNumber;
	private String unitRegistrationNo;
	private Boolean charterFlag;
	private String sectionCode;
	private String statusCode;
	private LocalDateTime dateApplied;
	private String officialReceiptNo;
	private LocalDate officialReceiptDate;
	private LocalDate expirationDate;
	private String councilRegistrationOfficer;
	private LocalDateTime councilProcessedDate;
	private String councilScoutExecutive;
	private LocalDate councilApprovedDate;
	private String regionalRegistrationOfficer;
	private LocalDateTime regionalProcessedDate;
	private String regionalScoutExecutive;
	private LocalDate regionalApprovedDate;

	private List<ISComDetailsEntity> iSComMembersList;
	private List<MemberDetailsEntity> unitMembersList;

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public Integer getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public String getUnitRegistrationNo() {
		return unitRegistrationNo;
	}

	public void setUnitRegistrationNo(String unitRegistrationNo) {
		this.unitRegistrationNo = unitRegistrationNo;
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

	public LocalDateTime getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(LocalDateTime dateApplied) {
		this.dateApplied = dateApplied;
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

	public List<ISComDetailsEntity> getIscomMembersList() {
		return iSComMembersList;
	}

	public void setIscomMembersList(List<ISComDetailsEntity> iSComMembersList) {
		this.iSComMembersList = iSComMembersList;
	}

	public List<MemberDetailsEntity> getUnitMembersList() {
		return unitMembersList;
	}

	public void setUnitMembersList(List<MemberDetailsEntity> unitMembersList) {
		this.unitMembersList = unitMembersList;
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
}
