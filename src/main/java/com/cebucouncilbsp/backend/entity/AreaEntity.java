/**
 *
 */
package com.cebucouncilbsp.backend.entity;

/**
 * @author reneir.val.t.perez
 *
 */
public class AreaEntity extends BaseEntity {

	private String areaCode;
	private String districtName;
	private String chairmanName;
	private String chairmanContactNo;
	private String commissionerName;
	private String commissionerContactNo;
	private String remarks;

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getChairmanName() {
		return chairmanName;
	}

	public void setChairmanName(String chairmanName) {
		this.chairmanName = chairmanName;
	}

	public String getChairmanContactNo() {
		return chairmanContactNo;
	}

	public void setChairmanContactNo(String chairmanContactNo) {
		this.chairmanContactNo = chairmanContactNo;
	}

	public String getCommissionerName() {
		return commissionerName;
	}

	public void setCommissionerName(String commissionerName) {
		this.commissionerName = commissionerName;
	}

	public String getCommissionerContactNo() {
		return commissionerContactNo;
	}

	public void setCommissionerContactNo(String commissionerContactNo) {
		this.commissionerContactNo = commissionerContactNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
