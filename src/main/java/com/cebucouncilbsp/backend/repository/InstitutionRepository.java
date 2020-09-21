/**
 *
 */
package com.cebucouncilbsp.backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cebucouncilbsp.backend.entity.AreaDistrictsEntity;
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

	InstitutionEntity findByInstitutionNameAreaDistrict(String institutionName, String area, String district);

	List<InstitutionEntity> findByArea(String area);

	List<InstitutionEntity> findByDistrict(String district);

	List<AreaDistrictsEntity> findAllDistinctDistricts();
}
