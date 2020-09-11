/**
 *
 */
package com.cebucouncilbsp.backend.repository;

import java.util.List;

import com.cebucouncilbsp.backend.entity.UnitNumberEntity;

/**
 * @author reneir.val.t.perez
 *
 */
public interface UnitNumberRepository {

	int insertUnitNumber(UnitNumberEntity unitNumber);

	int updateUnitNumber(UnitNumberEntity unitNumber);

	List<UnitNumberEntity> findAllUnitNumbers();

	List<UnitNumberEntity> findAvailableUnitNumbers(Integer currentYear, String sectionCode);

	List<UnitNumberEntity> findAvailableUnitNumbersExceptInstId(Integer institutionId, Integer currentYear,
			String sectionCode);

	UnitNumberEntity findByUnitNumber(String unitNumber);

	List<UnitNumberEntity> findByInstitutionId(Integer institutionId, Integer currentYear);

	List<UnitNumberEntity> findByInstitutionIdOnly(Integer institutionId);
}
