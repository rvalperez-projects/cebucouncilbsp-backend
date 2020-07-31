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
import org.springframework.stereotype.Service;

import com.cebucouncilbsp.backend.entity.InstitutionEntity;
import com.cebucouncilbsp.backend.entity.UserEntity;
import com.cebucouncilbsp.backend.exception.BusinessFailureException;
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
	public int signUp(UserSignUpRequestForm requestForm) {
		LOGGER.debug(MessageFormat.format("RequestForm: {0}", requestForm));
		LocalDateTime now = LocalDateTime.now();

		// Create User Entity to insert
		UserEntity user = new UserEntity();
		user.setUsername(requestForm.getUsername());
		user.setPassword(requestForm.getPassword());
		user.setAuthorityCode(requestForm.getAuthorityCode());
		user.setSurname(requestForm.getSurname());
		user.setGivenName(requestForm.getGivenName());
		user.setMiddleInitial(requestForm.getMiddleInitial());
		user.setMobileNumber(requestForm.getMobileNumber());
		user.setEmailAddress(requestForm.getEmailAddress());
		user.setCreatedBy(requestForm.getUsername());
		user.setUpdatedBy(requestForm.getUsername());
		user.setCreatedDateTime(now);
		user.setUpdatedDateTime(now);

		// Create Institution Entity to insert
		InstitutionEntity institution = new InstitutionEntity();
		institution.setInstitutionName(requestForm.getInstitutionName());
		institution.setDistrict(requestForm.getDistrict());
		institution.setArea(requestForm.getArea());
		institution.setAddress(requestForm.getAddress());
		institution.setContactNumber(requestForm.getContactNumber());
		institution.setCategoryCode(requestForm.getCategoryCode());
		institution.setCreatedBy(requestForm.getUsername());
		institution.setUpdatedBy(requestForm.getUsername());
		institution.setCreatedDateTime(now);
		institution.setUpdatedDateTime(now);

		// Insert UserEntity and InstitutionEntity to DB
		return userRepository.insertUser(user, institution);
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

		return userRepository.findByAreaDistrictInstitutionName(area, district, institutionId,
				requestForm.getName().toLowerCase());
	}

}
