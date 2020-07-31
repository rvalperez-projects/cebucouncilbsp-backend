/**
 *
 */
package com.cebucouncilbsp.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cebucouncilbsp.backend.entity.LoginResultEntity;
import com.cebucouncilbsp.backend.exception.BusinessFailureException;
import com.cebucouncilbsp.backend.repository.UserRepository;
import com.cebucouncilbsp.backend.requestdto.LoginRequestForm;
import com.cebucouncilbsp.backend.requestdto.LogoutRequestForm;

/**
 * @author reneir.val.t.perez
 *
 */
@Service
public class LoginService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

	private static final String USER_NOT_FOUND = "backend.error.auth.login.NotFound";

	@Autowired
	private UserRepository userRepository;

	public LoginResultEntity login(LoginRequestForm form) {
		LoginResultEntity result = userRepository.findByUsernamePassword(form.getUsername(), form.getPassword());
		if (result == null) {
			LOGGER.error("User account not found.");
			throw new BusinessFailureException(USER_NOT_FOUND);
		}
		return result;
	}

	public void logout(LogoutRequestForm form) {
		// ...
	}

}
