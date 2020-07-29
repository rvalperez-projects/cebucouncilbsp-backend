/**
 *
 */
package com.cebucouncilbsp.backend.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author reneir.val.t.perez
 *
 */
public class UnitRegistrationEntity extends BaseEntity {

	private Integer formId;
	private Integer institutionId;
	private String unitNumber;
	private String unitRegistrationNo;
	private String sectionCode;
	private String statusCode;
	private LocalDateTime dateApplied;
	private String officialReceiptNo;
	private LocalDate officialReceiptDate;
	private LocalDate expirationDate;

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
}
