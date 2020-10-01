package com.cebucouncilbsp.backend.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cebucouncilbsp.backend.annotation.AllUsers;
import com.cebucouncilbsp.backend.entity.AuthorityEntity;
import com.cebucouncilbsp.backend.entity.InstitutionEntity;
import com.cebucouncilbsp.backend.exception.BusinessFailureException;
import com.cebucouncilbsp.backend.requestdto.LoginRequestForm;
import com.cebucouncilbsp.backend.requestdto.LogoutRequestForm;
import com.cebucouncilbsp.backend.requestdto.UserSignUpRequestForm;
import com.cebucouncilbsp.backend.service.AreaService;
import com.cebucouncilbsp.backend.service.InstitutionService;
import com.cebucouncilbsp.backend.service.LoginService;
import com.cebucouncilbsp.backend.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private UserService userService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private InstitutionService institutionService;

	@PostMapping(path = "/login")
	@AllUsers
	public String login(@RequestBody @Valid LoginRequestForm form) throws Exception {
		return loginService.login(form);
	}

	@PostMapping(path = "/logout")
	@AllUsers
	public void login(@RequestBody @Valid LogoutRequestForm form) {
		loginService.logout(form);
	}

	@PostMapping(path = "/sign-up")
	@AllUsers
	public void signUp(@RequestBody @Valid UserSignUpRequestForm requestForm, Errors errors) {
		if (errors.hasErrors()) {
			throw new BusinessFailureException(errors);
		}
		AuthorityEntity user = new AuthorityEntity();
		user.setUsername(requestForm.getUsername());
		userService.signUp(requestForm, user);
	}

	@GetMapping(path = "/area/districts")
	@AllUsers
	public Map<String, List<String>> getAllDistinctDistricts() {
		return areaService.getAreasAndDistricts();
	}

	@GetMapping(path = "/{area}/{district}/institutions")
	@AllUsers
	public List<InstitutionEntity> getInstitutionsByAreaAndDistrict(@PathVariable String area,
			@PathVariable String district) {
		return institutionService.getInstitutionsByAreaAndDistrict(area, district);
	}
}
