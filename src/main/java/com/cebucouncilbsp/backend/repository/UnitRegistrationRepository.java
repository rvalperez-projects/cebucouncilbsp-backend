/**
 *
 */
package com.cebucouncilbsp.backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cebucouncilbsp.backend.entity.UnitRegistrationEntity;
import com.cebucouncilbsp.backend.entity.UnitRegistrationSearchResultEntity;

/**
 * @author reneir.val.t.perez
 *
 */
@Repository
public interface UnitRegistrationRepository {

	int insertUnitRegistrationEntityForm(UnitRegistrationEntity unitForm);

	int updateUnitRegistrationEntityForm(UnitRegistrationEntity unitForm);

	int updateFormStatus(UnitRegistrationEntity unitForm);

	List<UnitRegistrationEntity> findAllUnitRegistrations();

	UnitRegistrationEntity findByFormId(Integer formId);

	List<UnitRegistrationSearchResultEntity> findByAreaDistInstStatusName(String area, String district,
			Integer institutionId, String statusCode, String name);

	int deleteUnitRegistrationFormByFormId(Integer formId);
}
