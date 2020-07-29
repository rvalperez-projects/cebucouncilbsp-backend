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

	int insertUnitNumber(UnitNumberEntity area);

	int updateUnitNumber(UnitNumberEntity area);

	List<UnitNumberEntity> findAllUnitNumbers();

	UnitNumberEntity findByUnitNumber(String unitNumber);

	List<UnitNumberEntity> findByInstitutionId(Integer institutionId);
}
