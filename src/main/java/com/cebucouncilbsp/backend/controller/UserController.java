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

import com.cebucouncilbsp.backend.annotation.AdminAndCouncilOnly;
import com.cebucouncilbsp.backend.annotation.AdminUserOnly;
import com.cebucouncilbsp.backend.annotation.AllUsers;
import com.cebucouncilbsp.backend.entity.UserEntity;
import com.cebucouncilbsp.backend.entity.UserProfileEntity;
import com.cebucouncilbsp.backend.entity.UserSearchResultEntity;
import com.cebucouncilbsp.backend.exception.BusinessFailureException;
import com.cebucouncilbsp.backend.requestdto.SearchRequestForm;
import com.cebucouncilbsp.backend.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping(path = "/all")
	@AdminUserOnly
	public List<UserEntity> getAllUsers() {
		return service.getAll();
	}

	@GetMapping(path = "/{userId}")
	@AllUsers
	public UserProfileEntity getUserByUserId(@PathVariable Integer userId) {
		return service.getUserByUserId(userId);
	}

	@PostMapping(path = "/search")
	@AdminAndCouncilOnly
	public List<UserSearchResultEntity> searchUsers(@RequestBody @Valid SearchRequestForm requestForm, Errors errors) {
		if (errors.hasErrors()) {
			throw new BusinessFailureException(errors);
		}
		return service.searchUsers(requestForm);
	}
}
