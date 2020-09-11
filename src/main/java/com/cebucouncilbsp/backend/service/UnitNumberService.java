/**
 *
 */
package com.cebucouncilbsp.backend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cebucouncilbsp.backend.constant.InstitutionCategoryCode;
import com.cebucouncilbsp.backend.constant.ScoutingSectionCode;
import com.cebucouncilbsp.backend.entity.AuthorityEntity;
import com.cebucouncilbsp.backend.entity.InstitutionEntity;
import com.cebucouncilbsp.backend.entity.UnitNumberEntity;
import com.cebucouncilbsp.backend.entity.UnitNumberSearchResultEntity;
import com.cebucouncilbsp.backend.exception.BusinessFailureException;
import com.cebucouncilbsp.backend.repository.InstitutionRepository;
import com.cebucouncilbsp.backend.repository.UnitNumberRepository;
import com.cebucouncilbsp.backend.utils.DateUtils;

/**
 * @author reneir.val.t.perez
 *
 */
@Service
public class UnitNumberService {

	private static final String UNIT_NUMBER_NOT_FOUND = "backend.error.unitNumber.NotFound";
	private static final String UNIT_NUMBER_DUPLICATE = "backend.error.unitNumber.duplicate";
	private static final String SECTION_NOT_ALLOWED = "backend.error.unitNumber.sectionCode.NotAllowed";

	@Autowired
	private InstitutionRepository institutionRepository;
	@Autowired
	private UnitNumberRepository unitNumberRepository;

	/**
	 *
	 * @return
	 */
	public List<UnitNumberEntity> getAll() {
		return unitNumberRepository.findAllUnitNumbers();
	}

	/**
	 *
	 * @return
	 */
	public List<UnitNumberEntity> getUnitNumbersByInstitutionId(Integer institutionId) {
		return unitNumberRepository.findByInstitutionIdOnly(institutionId);
	}

	/**
	 *
	 * @return
	 */
	public UnitNumberSearchResultEntity searchUnitNumbers(Integer institutionId) {
		UnitNumberSearchResultEntity result = new UnitNumberSearchResultEntity();

		// Set Institution Unit Numbers
		result.setInstitutionUnitNumbers(unitNumberRepository.findByInstitutionIdOnly(institutionId));

		// Set Available Unit Numbers
		List<UnitNumberEntity> availableUnitNumbers = new ArrayList<>();
		List<ScoutingSectionCode> sectionCodes = new ArrayList<>();

		InstitutionEntity institution = institutionRepository.findByInstitutionId(institutionId);
		switch (InstitutionCategoryCode.get(institution.getCategoryCode())) {
		case PRESCHOOL:
			sectionCodes.add(ScoutingSectionCode.LANGKAY);
			sectionCodes.add(ScoutingSectionCode.KAWAN);
			break;
		case PRIMARY:
			sectionCodes.add(ScoutingSectionCode.LANGKAY);
			sectionCodes.add(ScoutingSectionCode.KAWAN);
			sectionCodes.add(ScoutingSectionCode.TROOP);
			break;
		case SECONDARY:
		case SENIOR_HIGH:
			sectionCodes.add(ScoutingSectionCode.OUTFIT);
			sectionCodes.add(ScoutingSectionCode.CIRCLE);
			break;
		case COLLEGE:
			sectionCodes.add(ScoutingSectionCode.CIRCLE);
			break;
		case COMMUNITY:
			sectionCodes.add(ScoutingSectionCode.LANGKAY);
			sectionCodes.add(ScoutingSectionCode.KAWAN);
			sectionCodes.add(ScoutingSectionCode.TROOP);
			sectionCodes.add(ScoutingSectionCode.OUTFIT);
			sectionCodes.add(ScoutingSectionCode.CIRCLE);
			break;
		}

		for (ScoutingSectionCode section : sectionCodes) {
			availableUnitNumbers.addAll(unitNumberRepository.findAvailableUnitNumbersExceptInstId(institutionId,
					DateUtils.getCurrentDate().getYear(), section.getCode()));
		}

		result.setAvailableUnitNumbers(availableUnitNumbers);
		return result;
	}

	/**
	 *
	 * @param areaCode
	 * @return
	 */
	public UnitNumberEntity getUnitNumber(String unitNumber) {
		return unitNumberRepository.findByUnitNumber(unitNumber);
	}

	public int createNewUnitNumber(UnitNumberEntity request, AuthorityEntity user) {

		// Check if Unit Number is already registered
		if (null != unitNumberRepository.findByUnitNumber(request.getUnitNumber())) {
			throw new BusinessFailureException(UNIT_NUMBER_DUPLICATE);
		}

		// Check if inputted Section Code is allowed in Institution Category type
		List<ScoutingSectionCode> notAllowed = new ArrayList<>();
		InstitutionEntity institution = institutionRepository.findByInstitutionId(request.getInstitutionId());
		switch (InstitutionCategoryCode.get(institution.getCategoryCode())) {
		case PRESCHOOL:
			notAllowed = Arrays.asList(ScoutingSectionCode.TROOP, ScoutingSectionCode.OUTFIT,
					ScoutingSectionCode.CIRCLE);
			break;
		case PRIMARY:
			notAllowed = Arrays.asList(ScoutingSectionCode.OUTFIT, ScoutingSectionCode.CIRCLE);
			break;
		case SECONDARY:
		case SENIOR_HIGH:
			notAllowed = Arrays.asList(ScoutingSectionCode.LANGKAY, ScoutingSectionCode.KAWAN,
					ScoutingSectionCode.TROOP);
			break;
		case COLLEGE:
			notAllowed = Arrays.asList(ScoutingSectionCode.LANGKAY, ScoutingSectionCode.KAWAN,
					ScoutingSectionCode.TROOP, ScoutingSectionCode.OUTFIT);
			break;
		case COMMUNITY:
			break;
		}
		if (notAllowed.contains(ScoutingSectionCode.get(request.getSectionCode()))) {
			throw new BusinessFailureException(SECTION_NOT_ALLOWED);
		}

		request.setCreatedBy(user.getUsername());
		request.setUpdatedBy(user.getUsername());
		request.setCreatedDateTime(DateUtils.getCurrentDateTime());
		request.setUpdatedDateTime(DateUtils.getCurrentDateTime());

		return unitNumberRepository.insertUnitNumber(request);
	}

	public int updateUnitNumberInstitution(List<UnitNumberEntity> unitNumberRequest, AuthorityEntity user) {
		int count = 0;
		for (UnitNumberEntity request : unitNumberRequest) {
			UnitNumberEntity unitNumberEntity = unitNumberRepository.findByUnitNumber(request.getUnitNumber());
			if (null == unitNumberEntity) {
				throw new BusinessFailureException(UNIT_NUMBER_NOT_FOUND);
			}
			unitNumberEntity.setUpdatedBy(user.getUsername());
			unitNumberEntity.setUpdatedDateTime(DateUtils.getCurrentDateTime());

			// Set Institution
			unitNumberEntity.setInstitutionId(request.getInstitutionId());
			unitNumberEntity.setLastUsedYear(null);
			count += unitNumberRepository.updateUnitNumber(unitNumberEntity);
		}
		return count;
	}

}
