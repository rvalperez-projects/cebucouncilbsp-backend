/**
 *
 */
package com.cebucouncilbsp.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cebucouncilbsp.backend.constant.AuthorityCategoryCode;
import com.cebucouncilbsp.backend.entity.AuthorityEntity;
import com.cebucouncilbsp.backend.entity.InstitutionEntity;
import com.cebucouncilbsp.backend.entity.UserEntity;
import com.cebucouncilbsp.backend.entity.UserProfileEntity;
import com.cebucouncilbsp.backend.entity.UserSearchResultEntity;
import com.cebucouncilbsp.backend.exception.UserNotFoundException;
import com.cebucouncilbsp.backend.repository.AuthorityRepository;
import com.cebucouncilbsp.backend.repository.InstitutionRepository;
import com.cebucouncilbsp.backend.repository.UserRepository;
import com.cebucouncilbsp.backend.requestdto.SearchRequestForm;
import com.cebucouncilbsp.backend.requestdto.UserSignUpRequestForm;
import com.cebucouncilbsp.backend.utils.DateUtils;

/**
 * @author reneir.val.t.perez
 *
 */
@Service
@Transactional
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	private static final String USER_NOT_FOUND = "backend.error.auth.login.NotFound";
	private static final String COUNCIL = "Council";
	private static final String DUMMY_PASS = "x----x";

	@Value("${auth.council.name}")
	private String councilName;

	@Value("${auth.council.address}")
	private String councilAddress;

	@Value("${auth.council.contactNumber}")
	private String councilContactNumber;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InstitutionRepository institutionRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private EmailService emailService;

	/**
	 *
	 * @return
	 */
	public List<UserEntity> getAll() {
		return userRepository.findAllUsers();
	}

	/**
	 *
	 * @param userId
	 * @return
	 */
	public UserProfileEntity getUserByUserId(Integer userId) {
		UserProfileEntity result = new UserProfileEntity();

		UserEntity user = userRepository.findByUserId(userId);
		result.setUserId(userId);
		result.setSurname(user.getSurname());
		result.setGivenName(user.getGivenName());
		result.setMiddleInitial(user.getMiddleInitial());
		result.setMobileNumber(user.getMobileNumber());
		result.setEmailAddress(user.getEmailAddress());

		// Password NOT returned back to user
		AuthorityEntity authority = authorityRepository.findAuthUserByUserId(userId);
		result.setUsername(authority.getUsername());
		result.setAuthorityCode(authority.getRoleCode());

		if (AuthorityCategoryCode.GENERAL_USER.getCode().equals(result.getAuthorityCode())) {
			result.setInstitutionId(user.getInstitutionId());
			InstitutionEntity institution = institutionRepository.findByInstitutionId(user.getInstitutionId());
			result.setInstitutionName(institution.getInstitutionName());
			result.setAddress(institution.getAddress());
			result.setDistrict(institution.getDistrict());
			result.setArea(institution.getArea());
			result.setCategoryCode(institution.getCategoryCode());
			result.setContactNumber(institution.getContactNumber());
		} else {
			if (AuthorityCategoryCode.COUNCIL.getCode().equals(result.getAuthorityCode())) {
				result.setArea(AuthorityCategoryCode.COUNCIL.name());
			} else if (AuthorityCategoryCode.ADMIN.getCode().equals(result.getAuthorityCode())) {
				result.setArea(AuthorityCategoryCode.ADMIN.name());
			}
			result.setAddress(this.councilAddress);
			result.setContactNumber(this.councilContactNumber);
		}

		return result;
	}

	/**
	 *
	 * @param requestForm
	 */
	public int signUp(UserSignUpRequestForm requestForm, AuthorityEntity accessingUser) {
		Integer userId = null;
		LocalDateTime now = DateUtils.getCurrentDateTime();

		// Create User Entity to insert
		UserEntity user = new UserEntity();
		user.setSurname(requestForm.getSurname());
		user.setGivenName(requestForm.getGivenName());
		user.setMiddleInitial(requestForm.getMiddleInitial());
		user.setMobileNumber(requestForm.getMobileNumber());
		user.setEmailAddress(requestForm.getEmailAddress());

		Integer institutionId = requestForm.getInstitutionId();

		// Create New Institution if institution ID is less than 0 (NEW)
		if (institutionId < 1 && AuthorityCategoryCode.GENERAL_USER.getCode().equals(requestForm.getAuthorityCode())) {
			LOGGER.debug("Insert New Institution.");
			InstitutionEntity institution = new InstitutionEntity();
			institution.setInstitutionName(requestForm.getInstitutionName());
			institution.setDistrict(requestForm.getDistrict());
			institution.setArea(requestForm.getArea());
			institution.setAddress(requestForm.getAddress());
			institution.setContactNumber(requestForm.getContactNumber());
			institution.setCategoryCode(requestForm.getCategoryCode());
			institution.setCreatedBy(accessingUser.getUsername());
			institution.setUpdatedBy(accessingUser.getUsername());
			institution.setCreatedDateTime(now);
			institution.setUpdatedDateTime(now);

			institutionId = institutionRepository.insertInstitution(institution);
		}
		user.setInstitutionId(institutionId);

		user.setCreatedBy(accessingUser.getUsername());
		user.setUpdatedBy(accessingUser.getUsername());
		user.setCreatedDateTime(now);
		user.setUpdatedDateTime(now);
		userId = userRepository.insertUser(user);

		// Create Authority Rights for User
		AuthorityEntity authority = new AuthorityEntity();
		authority.setUserId(userId);
		authority.setToken("dummy-token");
		authority.setUsername(requestForm.getUsername());
		authority.setPassword(new BCryptPasswordEncoder().encode(requestForm.getPassword()));
		authority.setRoleCode(requestForm.getAuthorityCode());
		authority.setCreatedBy(accessingUser.getUsername());
		authority.setUpdatedBy(accessingUser.getUsername());
		authority.setCreatedDateTime(now);
		authority.setUpdatedDateTime(now);
		authorityRepository.insertAuthority(authority);

		if (AuthorityCategoryCode.GENERAL_USER.getCode().equals(requestForm.getAuthorityCode())) {
			// Send email
			emailService.sendRegistrationInfo(requestForm);
		}

		// Insert UserEntity and InstitutionEntity to DB
		return userId;
	}

	/**
	 *
	 * @param requestForm
	 */
	public UserSignUpRequestForm updateUser(UserSignUpRequestForm requestForm, AuthorityEntity accessingUser) {
		LocalDateTime now = DateUtils.getCurrentDateTime();

		UserEntity user = userRepository.findByUserId(requestForm.getUserId());
		if (null == user) {
			throw new UserNotFoundException();
		}

		// Create User Entity to update
		user.setSurname(requestForm.getSurname());
		user.setGivenName(requestForm.getGivenName());
		user.setMiddleInitial(requestForm.getMiddleInitial());
		user.setMobileNumber(requestForm.getMobileNumber());
		user.setEmailAddress(requestForm.getEmailAddress());

		Integer institutionId = requestForm.getInstitutionId();

		// Create New Institution if institution ID is less than 0 (NEW)
		if (institutionId < 1 && AuthorityCategoryCode.GENERAL_USER.getCode().equals(requestForm.getAuthorityCode())) {
			LOGGER.debug("Insert New Institution.");
			InstitutionEntity institution = new InstitutionEntity();
			institution.setInstitutionName(requestForm.getInstitutionName());
			institution.setDistrict(requestForm.getDistrict());
			institution.setArea(requestForm.getArea());
			institution.setAddress(requestForm.getAddress());
			institution.setContactNumber(requestForm.getContactNumber());
			institution.setCategoryCode(requestForm.getCategoryCode());
			institution.setCreatedBy(accessingUser.getUsername());
			institution.setUpdatedBy(accessingUser.getUsername());
			institution.setCreatedDateTime(now);
			institution.setUpdatedDateTime(now);

			institutionId = institutionRepository.insertInstitution(institution);
		}
		user.setInstitutionId(institutionId);

		user.setUpdatedBy(accessingUser.getUsername());
		user.setUpdatedDateTime(now);
		userRepository.updateUser(user);

		// Update Authority Rights
		AuthorityEntity authority = authorityRepository.findAuthUserByUserId(requestForm.getUserId());
		authority.setUsername(requestForm.getUsername());
		authority.setRoleCode(requestForm.getAuthorityCode());
		authority.setUpdatedBy(accessingUser.getUsername());
		authority.setUpdatedDateTime(now);

		// Update password only if changed
		if (!requestForm.getPassword().equals(DUMMY_PASS)) {
			authority.setPassword(new BCryptPasswordEncoder().encode(requestForm.getPassword()));
		} else {
			requestForm.setPassword("<Same as before>");
		}
		authorityRepository.updateAuthority(authority);

		if (AuthorityCategoryCode.GENERAL_USER.getCode().equals(requestForm.getAuthorityCode())) {
			// Send email
			emailService.sendRegistrationInfo(requestForm);
		}

		// Return the data that was requested to update
		return requestForm;
	}

	/**
	 *
	 * @param requestForm
	 * @return
	 */
	public List<UserSearchResultEntity> searchUsers(SearchRequestForm requestForm) {
		String area = requestForm.getArea() == null ? null : requestForm.getArea();
		String name = StringUtils.hasText(requestForm.getName()) ? requestForm.getName().toLowerCase() : null;

		// Search all Council Users
		if (COUNCIL.equals(area)) {
			return userRepository.findCouncilUsers(name);
		}

		// Search General Users
		String district = requestForm.getDistrict() == null ? null : requestForm.getDistrict();
		Integer institutionId = requestForm.getInstitutionId() == null ? null : requestForm.getInstitutionId();

		return userRepository.findByAreaDistrictInstitutionName(area, district, institutionId, name);
	}

}
