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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cebucouncilbsp.backend.constant.FormStatusCode;
import com.cebucouncilbsp.backend.entity.AuthorityEntity;
import com.cebucouncilbsp.backend.entity.ISComDetailsEntity;
import com.cebucouncilbsp.backend.entity.InstitutionEntity;
import com.cebucouncilbsp.backend.entity.MemberDetailsEntity;
import com.cebucouncilbsp.backend.entity.UnitNumberEntity;
import com.cebucouncilbsp.backend.entity.UnitRegistrationEntity;
import com.cebucouncilbsp.backend.entity.UnitRegistrationSearchResultEntity;
import com.cebucouncilbsp.backend.entity.UserEntity;
import com.cebucouncilbsp.backend.exception.BusinessFailureException;
import com.cebucouncilbsp.backend.repository.ISComDetailsRepository;
import com.cebucouncilbsp.backend.repository.InstitutionRepository;
import com.cebucouncilbsp.backend.repository.MemberDetailsRepository;
import com.cebucouncilbsp.backend.repository.UnitNumberRepository;
import com.cebucouncilbsp.backend.repository.UnitRegistrationRepository;
import com.cebucouncilbsp.backend.repository.UserRepository;
import com.cebucouncilbsp.backend.requestdto.SearchRequestForm;
import com.cebucouncilbsp.backend.requestdto.UnitRegistrationFormRequestForm;
import com.cebucouncilbsp.backend.requestdto.UnitRegistrationISComRequestForm;
import com.cebucouncilbsp.backend.requestdto.UnitRegistrationMemberRequestForm;
import com.cebucouncilbsp.backend.utils.DateUtils;
import com.cebucouncilbsp.backend.utils.TextUtils;

import io.jsonwebtoken.lang.Collections;

/**
 * @author reneir.val.t.perez
 *
 */
@Service
@Transactional
public class UnitRegistrationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UnitRegistrationService.class);

	private static final String UNIT_REGISTRATION_FORM_NOT_FOUND = "backend.aur.submit.form.NotFound";
	private static final String UNIT_REGISTRATION_FORM_ALREADY_PROCESSED = "backend.aur.submit.form.AlreadyProcessed";
	private static final String NO_MORE_UNIT_NUMBERS = "backend.error.unitNumber.NoMore";

	private enum MethodCode {
		REGISTER, PROCESS_COUNCIL
	}

	@Value("${auth.council.executive}")
	private String cebuCouncilScoutExecutive;

	@Autowired
	private UnitRegistrationRepository unitRegistrationRepository;
	@Autowired
	private ISComDetailsRepository iSComDetailsRepository;
	@Autowired
	private MemberDetailsRepository memberDetailsRepository;
	@Autowired
	private UnitNumberRepository unitNumberRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private InstitutionRepository institutionRepository;
	@Autowired
	private EmailService emailService;

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

		UnitRegistrationEntity result = unitRegistrationRepository.findByFormId(formId);

		if (result == null) {
			LOGGER.error(MessageFormat.format("Form {0} not found.", formId));
			throw new BusinessFailureException(UNIT_REGISTRATION_FORM_NOT_FOUND);
		}

		result.setIscomMembersList(iSComDetailsRepository.findByFormId(formId));
		result.setUnitMembersList(memberDetailsRepository.findByFormId(formId));

		return result;
	}

	/**
	 *
	 * @param requestForm
	 * @param accessingUser
	 * @return Number of submitted forms.
	 */
	public int submit(UnitRegistrationFormRequestForm requestForm, AuthorityEntity accessingUser) {
		UnitNumberEntity unitNumber = null;
		if (null == requestForm.getUnitNumber() || requestForm.getUnitNumber().equals("New")) {

			List<UnitNumberEntity> availableUnitNumbersList = unitNumberRepository
					.findAvailableUnitNumbers(DateUtils.getCurrentDate().getYear(), requestForm.getSectionCode());
			if (CollectionUtils.isEmpty(availableUnitNumbersList)) {
				throw new BusinessFailureException(NO_MORE_UNIT_NUMBERS);
			}
			unitNumber = availableUnitNumbersList.get(0);
		} else {
			unitNumber = unitNumberRepository.findByUnitNumber(requestForm.getUnitNumber());
		}
		requestForm.setUnitNumber(unitNumber.getUnitNumber());

		// Set date to Pinas timezone
		requestForm.setDateApplied(DateUtils.getCurrentDateTime());

		UnitRegistrationEntity unitRegistrationForm = new UnitRegistrationEntity();
		this.setUnitRegistrationEntity(unitRegistrationForm, requestForm, accessingUser, MethodCode.REGISTER);

		// Insert UnitRegistrationEntity
		LocalDateTime now = DateUtils.getCurrentDateTime();
		unitRegistrationForm.setCreatedBy(accessingUser.getUsername());
		unitRegistrationForm.setUpdatedBy(accessingUser.getUsername());
		unitRegistrationForm.setCreatedDateTime(now);
		unitRegistrationForm.setUpdatedDateTime(now);
		Integer formId = unitRegistrationRepository.insertUnitRegistrationEntityForm(unitRegistrationForm);

		// Insert ISCOM Members
		if (!Collections.isEmpty(unitRegistrationForm.getIscomMembersList())) {
			for (ISComDetailsEntity iscom : unitRegistrationForm.getIscomMembersList()) {
				iscom.setCreatedBy(accessingUser.getUsername());
				iscom.setUpdatedBy(accessingUser.getUsername());
				iscom.setCreatedDateTime(now);
				iscom.setUpdatedDateTime(now);
			}
			iSComDetailsRepository.insertISComDetailsList(unitRegistrationForm.getIscomMembersList(), formId);
		}

		// Insert Unit Members
		if (!Collections.isEmpty(unitRegistrationForm.getUnitMembersList())) {
			for (MemberDetailsEntity member : unitRegistrationForm.getUnitMembersList()) {
				member.setCreatedBy(accessingUser.getUsername());
				member.setUpdatedBy(accessingUser.getUsername());
				member.setCreatedDateTime(now);
				member.setUpdatedDateTime(now);
			}
			memberDetailsRepository.insertMemberDetailsList(unitRegistrationForm.getUnitMembersList(), formId);
		}

		// Update Unit Number Last Used Year to this Year
		unitNumber.setInstitutionId(requestForm.getInstitutionId());
		unitNumber.setLastUsedYear(now.getYear());
		unitNumber.setUpdatedBy(accessingUser.getUsername());
		unitNumber.setUpdatedDateTime(now);
		unitNumberRepository.updateUnitNumber(unitNumber);

		// Send Email
		UserEntity user = userRepository.findByUserId(accessingUser.getUserId());
		InstitutionEntity institution = institutionRepository.findByInstitutionId(user.getInstitutionId());
		emailService.sendAURSubmissionEmail(user, institution, unitRegistrationForm);

		return 1;
	}

	/**
	 *
	 * @param formId
	 * @param accessingUser
	 * @return Number of submitted forms.
	 */
	public int deleteAURForm(Integer formId, AuthorityEntity accessingUser) {

		UnitRegistrationEntity unitRegistrationForm = unitRegistrationRepository.findByFormId(formId);
		if (null == unitRegistrationForm) {
			LOGGER.error(MessageFormat.format("Form {0} not found.", formId));
			throw new BusinessFailureException(UNIT_REGISTRATION_FORM_NOT_FOUND);
		}

		// Throw back error when Unit Registration is already processed
		if (!FormStatusCode.SUBMITTED.getCode().equals(unitRegistrationForm.getStatusCode())) {
			LOGGER.error(MessageFormat.format("Form {0} is already Processed.", formId));
			throw new BusinessFailureException(UNIT_REGISTRATION_FORM_ALREADY_PROCESSED);
		}

		// Delete Unit Registration Form Details
		iSComDetailsRepository.deleteISComMembersByFormId(formId);
		memberDetailsRepository.deleteUnitMembersByFormId(formId);
		unitRegistrationRepository.deleteUnitRegistrationFormByFormId(formId);

		// Update Unit Number back to NULL (unused)
		LocalDateTime now = DateUtils.getCurrentDateTime();
		UnitNumberEntity unitNumber = unitNumberRepository.findByUnitNumber(unitRegistrationForm.getUnitNumber());
		if (null != unitNumber) {
			unitNumber.setLastUsedYear(null);
			unitNumber.setUpdatedBy(accessingUser.getUsername());
			unitNumber.setUpdatedDateTime(now);
			unitNumberRepository.updateUnitNumber(unitNumber);
		}

		return 1;
	}

	/**
	 *
	 * @param requestForm
	 * @return
	 */
	public List<UnitRegistrationSearchResultEntity> searchForms(SearchRequestForm requestForm) {
		String area = requestForm.getArea() == null ? null : requestForm.getArea();
		String district = requestForm.getDistrict() == null ? null : requestForm.getDistrict();
		Integer institutionId = requestForm.getInstitutionId() == null ? null : requestForm.getInstitutionId();
		String name = StringUtils.hasText(requestForm.getName()) ? requestForm.getName().toLowerCase() : null;

		return unitRegistrationRepository.findByAreaDistInstStatusName(area, district, institutionId,
				requestForm.getStatusCode(), name);
	}

	public UnitRegistrationEntity updateStatus(Integer formId, String statusCode, AuthorityEntity accessingUser) {
		UnitRegistrationEntity entity = unitRegistrationRepository.findByFormId(formId);
		if (null == entity) {
			throw new BusinessFailureException(UNIT_REGISTRATION_FORM_NOT_FOUND);
		}

		entity.setStatusCode(statusCode);
		entity.setUpdatedBy(accessingUser.getUsername());
		entity.setUpdatedDateTime(DateUtils.getCurrentDateTime());

		unitRegistrationRepository.updateFormStatus(entity);
		return entity;
	}

	public UnitRegistrationEntity updateForm(UnitRegistrationFormRequestForm requestForm,
			AuthorityEntity accessingUser) {
		if (null == requestForm.getFormId()) {
			throw new BusinessFailureException(UNIT_REGISTRATION_FORM_NOT_FOUND);
		}

		UnitRegistrationEntity unitRegistrationForm = unitRegistrationRepository.findByFormId(requestForm.getFormId());
		if (null == unitRegistrationForm) {
			throw new BusinessFailureException(UNIT_REGISTRATION_FORM_NOT_FOUND);
		}

		this.setUnitRegistrationEntity(unitRegistrationForm, requestForm, accessingUser, MethodCode.PROCESS_COUNCIL);

		// Update unitRegistrationForm
		unitRegistrationRepository.updateUnitRegistrationEntityForm(unitRegistrationForm);
		iSComDetailsRepository.updateISComDetails(unitRegistrationForm.getIscomMembersList());
		memberDetailsRepository.updateMemberDetails(unitRegistrationForm.getUnitMembersList());

		return unitRegistrationForm;
	}

	private void setUnitRegistrationEntity(UnitRegistrationEntity unitRegistrationForm,
			UnitRegistrationFormRequestForm requestForm, AuthorityEntity accessingUser, MethodCode methodCode) {

		// Get current date and time
		LocalDateTime now = DateUtils.getCurrentDateTime();

		// Create Unit Registration Entity to insert
		unitRegistrationForm.setFormId(requestForm.getFormId());
		unitRegistrationForm.setInstitutionId(requestForm.getInstitutionId());
		unitRegistrationForm.setUnitRegistrationNo(requestForm.getUnitRegistrationNo());
		unitRegistrationForm.setUnitNumber(requestForm.getUnitNumber());
		unitRegistrationForm.setCharterFlag(requestForm.getCharterFlag());
		unitRegistrationForm.setSectionCode(requestForm.getSectionCode());
		unitRegistrationForm.setStatusCode(requestForm.getStatusCode());
		unitRegistrationForm.setOfficialReceiptDate(requestForm.getOfficialReceiptDate());
		unitRegistrationForm.setOfficialReceiptNo(requestForm.getOfficialReceiptNo());
		unitRegistrationForm.setDateApplied(requestForm.getDateApplied());

		if (MethodCode.REGISTER.equals(methodCode)) {
			unitRegistrationForm.setCreatedBy(accessingUser.getUsername());
			unitRegistrationForm.setCreatedDateTime(now);
		} else if (MethodCode.PROCESS_COUNCIL.equals(methodCode)) {
			// Set Expiration Date
			LocalDate expiryDate = now.plusYears(1).minusDays(1).toLocalDate();
			unitRegistrationForm.setExpirationDate(expiryDate);

			// Set Local Council Action details
			UserEntity user = userRepository.findByUserId(accessingUser.getUserId());
			String councilRegistrationOfficer = TextUtils.getFullName(user.getSurname(), user.getGivenName(),
					user.getMiddleInitial());
			unitRegistrationForm.setCouncilRegistrationOfficer(councilRegistrationOfficer);
			unitRegistrationForm.setCouncilProcessedDate(now);

			unitRegistrationForm.setCouncilScoutExecutive(cebuCouncilScoutExecutive);
			unitRegistrationForm.setCouncilApprovedDate(now.toLocalDate());
		}
		unitRegistrationForm.setUpdatedBy(accessingUser.getUsername());
		unitRegistrationForm.setUpdatedDateTime(now);

		List<ISComDetailsEntity> institutionalCommitteeList = new ArrayList<>();
		for (UnitRegistrationISComRequestForm iSCom : requestForm.getIscomMembersList()) {
			ISComDetailsEntity entity = new ISComDetailsEntity();
			entity.setFormId(requestForm.getFormId());
			entity.setIscomId(iSCom.getIscomId());
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

			if (methodCode.equals(MethodCode.REGISTER)) {
				entity.setCreatedBy(accessingUser.getUsername());
				entity.setCreatedDateTime(now);
			}
			entity.setUpdatedBy(accessingUser.getUsername());
			entity.setUpdatedDateTime(now);
			institutionalCommitteeList.add(entity);
		}
		unitRegistrationForm.setIscomMembersList(institutionalCommitteeList);

		List<MemberDetailsEntity> patrolMembersList = new ArrayList<>();
		for (UnitRegistrationMemberRequestForm member : requestForm.getUnitMembersList()) {
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

			if (MethodCode.REGISTER.equals(methodCode)) {
				entity.setCreatedBy(accessingUser.getUsername());
				entity.setCreatedDateTime(now);
			}
			entity.setUpdatedBy(accessingUser.getUsername());
			entity.setUpdatedDateTime(now);
			patrolMembersList.add(entity);
		}
		unitRegistrationForm.setUnitMembersList(patrolMembersList);
	}

	public int sendReceiptToEmail(MultipartFile receipt, Integer formId, AuthorityEntity accessingUser) {

		// Get information
		UserEntity user = userRepository.findByUserId(accessingUser.getUserId());
		InstitutionEntity institution = institutionRepository.findByInstitutionId(user.getInstitutionId());
		UnitRegistrationEntity unitRegistration = unitRegistrationRepository.findByFormId(formId);
		unitRegistration.setIscomMembersList(iSComDetailsRepository.findByFormId(formId));
		unitRegistration.setUnitMembersList(memberDetailsRepository.findByFormId(formId));

		// Send Email
		emailService.sendAURReceiptEmail(receipt, user, institution, unitRegistration);

		// Set Paid status to True
		unitRegistration.setStatusCode(FormStatusCode.PAID.getCode());
		unitRegistration.setUpdatedBy(accessingUser.getUsername());
		unitRegistration.setUpdatedDateTime(DateUtils.getCurrentDateTime());
		return unitRegistrationRepository.updateFormStatus(unitRegistration);
	}
}
