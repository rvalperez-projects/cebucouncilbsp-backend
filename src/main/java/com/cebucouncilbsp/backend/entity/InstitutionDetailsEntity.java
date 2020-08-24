/**
 *
 */
package com.cebucouncilbsp.backend.entity;

import java.util.List;

/**
 * @author reneir.val.t.perez
 *
 */
public class InstitutionDetailsEntity {

	private Integer institutionId;
	private String institutionName;
	private String district;
	private String area;
	private List<String> unitNumbers;

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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public List<String> getUnitNumbers() {
		return unitNumbers;
	}

	public void setUnitNumbers(List<String> unitNumbers) {
		this.unitNumbers = unitNumbers;
	}
}
