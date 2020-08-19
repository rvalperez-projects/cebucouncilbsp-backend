/**
 *
 */
package com.cebucouncilbsp.backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cebucouncilbsp.backend.entity.InstitutionEntity;
import com.cebucouncilbsp.backend.repository.InstitutionRepository;

/**
 * @author reneir.val.t.perez
 *
 */
@Service
public class InstitutionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(InstitutionService.class);

	@Autowired
	private InstitutionRepository institutionRepository;

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

}
