package com.cebucouncilbsp.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cebucouncilbsp.backend.entity.LoginResultEntity;
import com.cebucouncilbsp.backend.requestdto.LoginRequestForm;
import com.cebucouncilbsp.backend.requestdto.LogoutRequestForm;
import com.cebucouncilbsp.backend.service.LoginService;

@RestController
@RequestMapping(value = "/auth")
public class LoginController {

	@Autowired
	private LoginService service;

	@PostMapping(path = "/login")
	public LoginResultEntity login(@RequestBody @Valid LoginRequestForm form) {
		return service.login(form);
	}

	@PostMapping(path = "/logout")
	public void login(@RequestBody @Valid LogoutRequestForm form) {
		service.logout(form);
	}

}
