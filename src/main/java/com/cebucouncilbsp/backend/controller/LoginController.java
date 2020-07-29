package com.cebucouncilbsp.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cebucouncilbsp.backend.service.LoginService;

@RestController
@RequestMapping(value = "/auth")
public class LoginController {

	@Autowired
	private LoginService service;

//	@PostMapping(path = "/members", consumes = "application/json", produces = "application/json")
//	public LoginResultEntity login(@RequestBody LoginRequestForm form) {
//		return service.login(form);
//	}

}
