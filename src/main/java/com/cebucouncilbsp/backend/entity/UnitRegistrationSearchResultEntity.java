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
public class UnitRegistrationSearchResultEntity {

	private Integer formId;
	private String unitRegistrationNo;
	private String institutionName;
	private String district;
	private String statusCode;
	private LocalDate dateApplied;
	private LocalDateTime updatedDateTime;

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

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public LocalDate getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(LocalDate dateApplied) {
		this.dateApplied = dateApplied;
	}

	public LocalDateTime getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	@Override
	public String toString() {
		return "UnitRegistrationSearchResultEntity [formId=" + formId + ", unitRegistrationNo=" + unitRegistrationNo
				+ ", institutionName=" + institutionName + ", district=" + district + ", statusCode=" + statusCode
				+ ", dateApplied=" + dateApplied + ", updatedDateTime=" + updatedDateTime + "]";
	}

}
