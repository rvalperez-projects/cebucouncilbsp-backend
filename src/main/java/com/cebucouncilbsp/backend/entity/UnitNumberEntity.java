/**
 *
 */
package com.cebucouncilbsp.backend.entity;

/**
 * @author reneir.val.t.perez
 *
 */
public class UnitNumberEntity extends BaseEntity {

	private String unitNumber;
	private Integer institutionId;
	private String sectionCode;
	private String lastUsedYear;

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public Integer getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}

	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public String getLastUsedYear() {
		return lastUsedYear;
	}

	public void setLastUsedYear(String lastUsedYear) {
		this.lastUsedYear = lastUsedYear;
	}
}
