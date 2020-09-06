/**
 *
 */
package com.cebucouncilbsp.backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cebucouncilbsp.backend.entity.AreaDistrictsEntity;
import com.cebucouncilbsp.backend.entity.InstitutionEntity;
import com.cebucouncilbsp.backend.entity.UnitNumberEntity;
import com.cebucouncilbsp.backend.repository.InstitutionRepository;
import com.cebucouncilbsp.backend.repository.UnitNumberRepository;
import com.cebucouncilbsp.backend.utils.DateUtils;

/**
 * @author reneir.val.t.perez
 *
 */
@Service
public class InstitutionService {

	@Autowired
	private InstitutionRepository institutionRepository;
	@Autowired
	private UnitNumberRepository unitNumberRepository;

	/**
	 *
	 * @return
	 */
	public List<InstitutionEntity> getAll() {
		return institutionRepository.findAllInstitutions();
	}

	/**
	 *
	 * @param institutionId
	 * @return
	 */
	public InstitutionEntity getIntitutionById(Integer institutionId) {
		return institutionRepository.findByInstitutionId(institutionId);
	}

	public List<UnitNumberEntity> getInstitutionUnitNumbers(Integer institutionId) {
		return unitNumberRepository.findByInstitutionId(institutionId, DateUtils.getCurrentDate().getYear());
	}

	public List<AreaDistrictsEntity> getAllDistinctDistricts() {
		return institutionRepository.findAllDistinctDistricts();
	}

}
