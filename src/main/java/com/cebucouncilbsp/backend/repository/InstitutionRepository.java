/**
 *
 */
package com.cebucouncilbsp.backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cebucouncilbsp.backend.entity.InstitutionEntity;

/**
 * @author reneir.val.t.perez
 *
 */
@Repository
public interface InstitutionRepository {

	int insertInstitution(InstitutionEntity institution);

	int updateInstitution(InstitutionEntity institution);

	List<InstitutionEntity> findAllInstitutions();

	InstitutionEntity findByInstitutionId(Integer institutionId);

	List<InstitutionEntity> findByArea(String area);
}
