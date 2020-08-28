/**
 *
 */
package com.cebucouncilbsp.backend.service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cebucouncilbsp.backend.entity.AuthorityEntity;
import com.cebucouncilbsp.backend.entity.InstitutionEntity;
import com.cebucouncilbsp.backend.entity.UserEntity;
import com.cebucouncilbsp.backend.exception.BusinessFailureException;
import com.cebucouncilbsp.backend.repository.AuthorityRepository;
import com.cebucouncilbsp.backend.repository.InstitutionRepository;
import com.cebucouncilbsp.backend.repository.UserRepository;
import com.cebucouncilbsp.backend.requestdto.SearchRequestForm;
import com.cebucouncilbsp.backend.requestdto.UserSignUpRequestForm;

/**
 * @author reneir.val.t.perez
 *
 */
@Service
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	private static final String USER_NOT_FOUND = "backend.error.auth.login.NotFound";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InstitutionRepository institutionRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

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
	public UserEntity getUserByUserId(Integer userId) {
		LOGGER.debug(MessageFormat.format("UserId: {0}", userId));
		UserEntity result = userRepository.findByUserId(userId);
		if (result == null) {
			LOGGER.error(MessageFormat.format("User {0} not found.", userId));
			throw new BusinessFailureException(USER_NOT_FOUND);
		}
		return result;
	}

	/**
	 *
	 * @param requestForm
	 */
	@Transactional
	public int signUp(UserSignUpRequestForm requestForm, AuthorityEntity accessingUser) {
		Integer userId = null;
		LocalDateTime now = LocalDateTime.now();

		// Create Institution Entity to insert
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
		Integer institutionId = institutionRepository.insertInstitution(institution);

		// Create User Entity to insert
		UserEntity user = new UserEntity();
		user.setSurname(requestForm.getSurname());
		user.setGivenName(requestForm.getGivenName());
		user.setMiddleInitial(requestForm.getMiddleInitial());
		user.setMobileNumber(requestForm.getMobileNumber());
		user.setEmailAddress(requestForm.getEmailAddress());
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

		// Insert UserEntity and InstitutionEntity to DB
		return userId;
	}

	/**
	 *
	 * @param requestForm
	 * @return
	 */
	public List<UserEntity> searchUsers(SearchRequestForm requestForm) {
		LOGGER.debug(MessageFormat.format("RequestForm: {0}", requestForm));
		String area = requestForm.getArea() == null ? null : requestForm.getArea();
		String district = requestForm.getDistrict() == null ? null : requestForm.getDistrict();
		Integer institutionId = requestForm.getInstitutionId() == null ? null : requestForm.getInstitutionId();
		String name = requestForm.getName() == null ? null : requestForm.getName().toLowerCase();

		return userRepository.findByAreaDistrictInstitutionName(area, district, institutionId, name);
	}

}
