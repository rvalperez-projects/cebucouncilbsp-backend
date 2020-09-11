/**
 *
 */
package com.cebucouncilbsp.backend.entity;

import java.util.List;

/**
 * @author reneir.val.t.perez
 *
 */
public class UnitNumberSearchResultEntity {

	private List<UnitNumberEntity> institutionUnitNumbers;
	private List<UnitNumberEntity> availableUnitNumbers;

	public List<UnitNumberEntity> getInstitutionUnitNumbers() {
		return institutionUnitNumbers;
	}

	public void setInstitutionUnitNumbers(List<UnitNumberEntity> institutionUnitNumbers) {
		this.institutionUnitNumbers = institutionUnitNumbers;
	}

	public List<UnitNumberEntity> getAvailableUnitNumbers() {
		return availableUnitNumbers;
	}

	public void setAvailableUnitNumbers(List<UnitNumberEntity> availableUnitNumbers) {
		this.availableUnitNumbers = availableUnitNumbers;
	}
}
