/**
 *
 */
package com.cebucouncilbsp.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cebucouncilbsp.backend.constant.AuthorityCategoryCode;
import com.cebucouncilbsp.backend.entity.AuthorityEntity;
import com.cebucouncilbsp.backend.entity.InstitutionEntity;
import com.cebucouncilbsp.backend.entity.LoginResultEntity;
import com.cebucouncilbsp.backend.entity.UserEntity;
import com.cebucouncilbsp.backend.exception.UserNotFoundException;
import com.cebucouncilbsp.backend.repository.AuthorityRepository;
import com.cebucouncilbsp.backend.repository.InstitutionRepository;
import com.cebucouncilbsp.backend.repository.UserRepository;
import com.cebucouncilbsp.backend.requestdto.LoginRequestForm;
import com.cebucouncilbsp.backend.requestdto.LogoutRequestForm;
import com.cebucouncilbsp.backend.security.JwtTokenUtils;
import com.cebucouncilbsp.backend.security.SecurityUserDetailService;

/**
 * @author reneir.val.t.perez
 *
 */
@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private InstitutionRepository institutionRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private SecurityUserDetailService userDetailsService;

	@Autowired
	private JwtTokenUtils jwtTokenUtil;

	/**
	 *
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public String login(LoginRequestForm form) {
		// Authenticate User
		this.authenticate(form.getUsername(), form.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(form.getUsername());
		return jwtTokenUtil.generateToken(userDetails);
	}

	@Transactional
	public LoginResultEntity getLoginResults(String username) {

		LoginResultEntity loginResult = new LoginResultEntity();
		AuthorityEntity authorityResult = authorityRepository.findAuthUserByUsername(username);
		loginResult.setUserId(authorityResult.getUserId());
		loginResult.setRoleCode(authorityResult.getRoleCode());

		UserEntity user = userRepository.findByUserId(authorityResult.getUserId());
		loginResult.setGivenName(user.getGivenName());

		if (authorityResult.getRoleCode().equals(AuthorityCategoryCode.GENERAL_USER.getCode())) {
			UserEntity userResult = userRepository.findByUserId(authorityResult.getUserId());
			InstitutionEntity institutionResult = institutionRepository
					.findByInstitutionId(userResult.getInstitutionId());

			loginResult.setInstitutionId(institutionResult.getInstitutionId());
			loginResult.setArea(institutionResult.getArea());
		}

		return loginResult;
	}

	public void logout(LogoutRequestForm form) {
		// ...
	}

	private Authentication authenticate(String username, String password) {
		try {
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException | BadCredentialsException e) {
			throw new UserNotFoundException();
		}
	}

}
