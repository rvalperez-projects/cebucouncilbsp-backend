package com.cebucouncilbsp.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cebucouncilbsp.backend.entity.UserEntity;
import com.cebucouncilbsp.backend.exception.BusinessFailureException;
import com.cebucouncilbsp.backend.requestdto.SearchRequestForm;
import com.cebucouncilbsp.backend.requestdto.UserSignUpRequestForm;
import com.cebucouncilbsp.backend.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping(path = "/all")
	public List<UserEntity> getAllUsers() {
		return service.getAll();
	}

	@GetMapping(path = "/{userId}")
	public UserEntity getUserByUserId(@PathVariable Integer userId) {
		return service.getUserByUserId(userId);
	}

	@PostMapping(path = "/sign-up")
	public void signUp(@RequestBody @Valid UserSignUpRequestForm requestForm, Errors errors) {
		if (errors.hasErrors()) {
			throw new BusinessFailureException(errors);
		}
		service.signUp(requestForm);
	}

	@PostMapping(path = "/search")
	public List<UserEntity> searchUsers(@RequestBody @Valid SearchRequestForm requestForm, Errors errors) {
		if (errors.hasErrors()) {
			throw new BusinessFailureException(errors);
		}
		return service.searchUsers(requestForm);
	}
}
