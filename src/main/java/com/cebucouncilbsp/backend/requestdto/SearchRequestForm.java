/**
 *
 */
package com.cebucouncilbsp.backend.requestdto;

import javax.validation.constraints.NotNull;

import com.cebucouncilbsp.backend.constant.FormStatusCode;

/**
 * @author reneir.val.t.perez
 *
 */
public class SearchRequestForm {

	private String area;
	private String district;
	private Integer institutionId;
	@NotNull
	private String name;
	private String statusCode = FormStatusCode.SUBMITTED.getCode();

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "SearchRequestForm [area=" + area + ", district=" + district + ", institutionId=" + institutionId
				+ ", name=" + name + "]";
	}

}
