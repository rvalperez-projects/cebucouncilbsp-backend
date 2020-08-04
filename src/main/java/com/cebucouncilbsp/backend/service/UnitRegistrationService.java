/**
 *
 */
package com.cebucouncilbsp.backend.service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.cebucouncilbsp.backend.entity.AuthorityEntity;
import com.cebucouncilbsp.backend.entity.ISComDetailsEntity;
import com.cebucouncilbsp.backend.entity.MemberDetailsEntity;
import com.cebucouncilbsp.backend.entity.UnitNumberEntity;
import com.cebucouncilbsp.backend.entity.UnitRegistrationEntity;
import com.cebucouncilbsp.backend.entity.UnitRegistrationSearchResultEntity;
import com.cebucouncilbsp.backend.exception.BusinessFailureException;
import com.cebucouncilbsp.backend.repository.ISComDetailsRepository;
import com.cebucouncilbsp.backend.repository.MemberDetailsRepository;
import com.cebucouncilbsp.backend.repository.UnitNumberRepository;
import com.cebucouncilbsp.backend.repository.UnitRegistrationRepository;
import com.cebucouncilbsp.backend.requestdto.SearchRequestForm;
import com.cebucouncilbsp.backend.requestdto.UnitRegistrationFormRequestForm;
import com.cebucouncilbsp.backend.requestdto.UnitRegistrationISComRequestForm;
import com.cebucouncilbsp.backend.requestdto.UnitRegistrationMemberRequestForm;

/**
 * @author reneir.val.t.perez
 *
 */
@Service
public class UnitRegistrationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UnitRegistrationService.class);

	private static final String UNIT_REGISTRATION_FORM_NOT_FOUND = "backend.aur.submit.form.NotFound";
	private static final String NO_MORE_UNIT_NUMBERS = "backend.error.unitNumber.NoMore";

	private enum MethodCode {
		INSERT, UPDATE
	}

	@Autowired
	private UnitRegistrationRepository unitRegistrationRepository;
	@Autowired
	private ISComDetailsRepository iSComDetailsRepository;
	@Autowired
	private MemberDetailsRepository memberDetailsRepository;
	@Autowired
	private UnitNumberRepository unitNumberRepository;

	/**
	 *
	 * @return
	 */
	public List<UnitRegistrationEntity> getAll() {
		return unitRegistrationRepository.findAllUnitRegistrations();
	}

	/**
	 *
	 * @param formId
	 * @return
	 */
	public UnitRegistrationEntity getByFormId(Integer formId) {
		LOGGER.debug(MessageFormat.format("Form: {0}", formId));

		UnitRegistrationEntity result = unitRegistrationRepository.findByFormId(formId);

		if (result == null) {
			LOGGER.error(MessageFormat.format("Form {0} not found.", formId));
			throw new BusinessFailureException(UNIT_REGISTRATION_FORM_NOT_FOUND);
		}

		result.setInstitutionalCommitteeList(iSComDetailsRepository.findByFormId(formId));
		result.setPatrolMembersList(memberDetailsRepository.findByFormId(formId));

		return result;
	}

	/**
	 *
	 * @param requestForm
	 * @return Number of submitted forms.
	 */
	public int submit(UnitRegistrationFormRequestForm requestForm, AuthorityEntity accessingUser) {
		LOGGER.debug(MessageFormat.format("RequestForm: {0}", requestForm));

		if (null == requestForm.getUnitNumber()) {

			List<UnitNumberEntity> availableUnitNumbersList = unitNumberRepository
					.findAvailableUnitNumbers(LocalDate.now().getYear(), requestForm.getSectionCode());
			if (CollectionUtils.isEmpty(availableUnitNumbersList)) {
				throw new BusinessFailureException(NO_MORE_UNIT_NUMBERS);
			}
			requestForm.setUnitNumber(availableUnitNumbersList.get(0).getUnitNumber());
		}

		UnitRegistrationEntity unitRegistrationForm = new UnitRegistrationEntity();
		this.setUnitRegistrationEntity(unitRegistrationForm, requestForm, accessingUser, MethodCode.INSERT);

		// Insert UnitRegistrationEntity and InstitutionEntity to DB
		return unitRegistrationRepository.insertUnitRegistrationEntityForm(unitRegistrationForm);
	}

	/**
	 *
	 * @param requestForm
	 * @return
	 */
	public List<UnitRegistrationSearchResultEntity> searchForms(SearchRequestForm requestForm) {
		LOGGER.debug(MessageFormat.format("RequestForm: {0}", requestForm));
		String area = requestForm.getArea() == null ? null : requestForm.getArea();
		String district = requestForm.getDistrict() == null ? null : requestForm.getDistrict();
		Integer institutionId = requestForm.getInstitutionId() == null ? null : requestForm.getInstitutionId();

		return unitRegistrationRepository.findByAreaDistInstStatusName(area, district, institutionId,
				requestForm.getStatusCode(), requestForm.getName().toLowerCase());
	}

	public UnitRegistrationEntity updateStatus(Integer formId, String statusCode, AuthorityEntity accessingUser) {
		UnitRegistrationEntity entity = unitRegistrationRepository.findByFormId(formId);
		if (null == entity) {
			throw new BusinessFailureException(UNIT_REGISTRATION_FORM_NOT_FOUND);
		}

		entity.setStatusCode(statusCode);
		entity.setUpdatedBy(accessingUser.getUsername());
		entity.setUpdatedDateTime(LocalDateTime.now());

		unitRegistrationRepository.updateFormStatus(entity);
		return entity;
	}

	@Transactional
	public UnitRegistrationEntity updateForm(UnitRegistrationFormRequestForm requestForm,
			AuthorityEntity accessingUser) {
		if (null == requestForm.getFormId()) {
			throw new BusinessFailureException(UNIT_REGISTRATION_FORM_NOT_FOUND);
		}

		UnitRegistrationEntity unitRegistrationForm = unitRegistrationRepository.findByFormId(requestForm.getFormId());
		if (null == unitRegistrationForm) {
			throw new BusinessFailureException(UNIT_REGISTRATION_FORM_NOT_FOUND);
		}

		this.setUnitRegistrationEntity(unitRegistrationForm, requestForm, accessingUser, MethodCode.UPDATE);

		// Update unitRegistrationForm
		unitRegistrationRepository.updateUnitRegistrationEntityForm(unitRegistrationForm);
		iSComDetailsRepository.updateISComDetails(unitRegistrationForm.getInstitutionalCommitteeList());
		memberDetailsRepository.updateMemberDetails(unitRegistrationForm.getPatrolMembersList());

		return unitRegistrationForm;
	}

	private void setUnitRegistrationEntity(UnitRegistrationEntity unitRegistrationForm,
			UnitRegistrationFormRequestForm requestForm, AuthorityEntity accessingUser, MethodCode methodCode) {

		// Get current date and time
		LocalDateTime now = LocalDateTime.now();

		// Create Unit Registration Entity to insert
		unitRegistrationForm.setFormId(requestForm.getFormId());
		unitRegistrationForm.setInstitutionId(requestForm.getInstitutionId());
		unitRegistrationForm.setUnitRegistrationNo(requestForm.getUnitRegistrationNo());
		unitRegistrationForm.setUnitNumber(requestForm.getUnitNumber());
		unitRegistrationForm.setSectionCode(requestForm.getSectionCode());
		unitRegistrationForm.setStatusCode(requestForm.getStatusCode());
		unitRegistrationForm.setOfficialReceiptDate(requestForm.getOfficialReceiptDate());
		unitRegistrationForm.setOfficialReceiptNo(requestForm.getOfficialReceiptNo());

		if (methodCode.equals(MethodCode.INSERT)) {
			unitRegistrationForm.setDateApplied(now);
			unitRegistrationForm.setExpirationDate(now.toLocalDate().plusYears(1L));
			unitRegistrationForm.setCreatedBy(accessingUser.getUsername());
			unitRegistrationForm.setCreatedDateTime(now);
		} else if (methodCode.equals(MethodCode.UPDATE)) {
			unitRegistrationForm.setDateApplied(requestForm.getDateApplied());
			unitRegistrationForm.setExpirationDate(requestForm.getExpirationDate());
		}
		unitRegistrationForm.setUpdatedBy(accessingUser.getUsername());
		unitRegistrationForm.setUpdatedDateTime(now);

		List<ISComDetailsEntity> institutionalCommitteeList = new ArrayList<>();
		for (UnitRegistrationISComRequestForm iSCom : requestForm.getInstitutionalCommitteeList()) {
			ISComDetailsEntity entity = new ISComDetailsEntity();
			entity.setFormId(requestForm.getFormId());
			entity.setISComId(iSCom.getCommitteeMemberId());
			entity.setPositionCode(iSCom.getPositionCode());
			entity.setSurname(iSCom.getSurname());
			entity.setGivenName(iSCom.getGivenName());
			entity.setMiddleInitial(iSCom.getMiddleInitial());
			entity.setSignature(iSCom.getSignature());
			entity.setAge(iSCom.getAge());
			entity.setMembershipCertNo(iSCom.getMembershipCertNo());
			entity.setHighestTrainingCode(iSCom.getHighestTrainingCode());
			entity.setTenure(iSCom.getTenure());
			entity.setReligion(iSCom.getReligion());

			if (methodCode.equals(MethodCode.INSERT)) {
				entity.setCreatedBy(accessingUser.getUsername());
				entity.setCreatedDateTime(now);
			}
			entity.setUpdatedBy(accessingUser.getUsername());
			entity.setUpdatedDateTime(now);
			institutionalCommitteeList.add(entity);
		}
		unitRegistrationForm.setInstitutionalCommitteeList(institutionalCommitteeList);

		List<MemberDetailsEntity> patrolMembersList = new ArrayList<>();
		for (UnitRegistrationMemberRequestForm member : requestForm.getPatrolMembersList()) {
			MemberDetailsEntity entity = new MemberDetailsEntity();
			entity.setFormId(requestForm.getFormId());
			entity.setMemberId(member.getMemberId());
			entity.setPositionCode(member.getPositionCode());
			entity.setSurname(member.getSurname());
			entity.setGivenName(member.getGivenName());
			entity.setMiddleInitial(member.getMiddleInitial());
			entity.setRegistrationStatusCode(member.getRegistrationStatusCode());
			entity.setAge(member.getAge());
			entity.setMembershipCertNo(member.getMembershipCertNo());
			entity.setHighestBadgeCode(member.getHighestBadgeCode());
			entity.setTenure(member.getTenure());
			entity.setReligion(member.getReligion());

			if (methodCode.equals(MethodCode.INSERT)) {
				entity.setCreatedBy(accessingUser.getUsername());
				entity.setCreatedDateTime(now);
			}
			entity.setUpdatedBy(accessingUser.getUsername());
			entity.setUpdatedDateTime(now);
			patrolMembersList.add(entity);
		}
		unitRegistrationForm.setPatrolMembersList(patrolMembersList);
	}
}
