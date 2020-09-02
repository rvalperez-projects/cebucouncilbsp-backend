package com.cebucouncilbsp.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cebucouncilbsp.backend.annotation.AccessingUser;
import com.cebucouncilbsp.backend.annotation.AdminAndCouncilOnly;
import com.cebucouncilbsp.backend.annotation.AdminUserOnly;
import com.cebucouncilbsp.backend.annotation.AllUsers;
import com.cebucouncilbsp.backend.annotation.CouncilAndUserOnly;
import com.cebucouncilbsp.backend.entity.AuthorityEntity;
import com.cebucouncilbsp.backend.entity.UnitRegistrationEntity;
import com.cebucouncilbsp.backend.entity.UnitRegistrationSearchResultEntity;
import com.cebucouncilbsp.backend.exception.BusinessFailureException;
import com.cebucouncilbsp.backend.requestdto.SearchRequestForm;
import com.cebucouncilbsp.backend.requestdto.UnitRegistrationFormRequestForm;
import com.cebucouncilbsp.backend.service.UnitRegistrationService;
import com.cebucouncilbsp.backend.validator.UnitRegistrationRequestFormValidator;

@RestController
@RequestMapping(value = "/form")
public class UnitRegistrationController {

	@Autowired
	private UnitRegistrationService service;

	@Autowired
	private UnitRegistrationRequestFormValidator validator;

	@GetMapping(path = "/all")
	@AdminUserOnly
	public List<UnitRegistrationEntity> getAllUsers() {
		return service.getAll();
	}

	@GetMapping(path = "/{formId}")
	@AllUsers
	public UnitRegistrationEntity getUserByUserId(@PathVariable Integer formId) {
		return service.getByFormId(formId);
	}

	@PostMapping(path = "/submit")
	@CouncilAndUserOnly
	public int submit(@RequestBody @Valid UnitRegistrationFormRequestForm requestForm,
			@AccessingUser AuthorityEntity user, Errors errors) {
		validator.validate(requestForm, errors);
		if (errors.hasErrors()) {
			throw new BusinessFailureException(errors);
		}
		return service.submit(requestForm, user);
	}

	@PostMapping(path = "/search")
	@AllUsers
	public List<UnitRegistrationSearchResultEntity> searchForms(@RequestBody @Valid SearchRequestForm requestForm,
			Errors errors) {
		if (errors.hasErrors()) {
			throw new BusinessFailureException(errors);
		}
		return service.searchForms(requestForm);
	}

	@PutMapping(path = "/update")
	@AdminAndCouncilOnly
	public UnitRegistrationEntity updateForm(@RequestBody UnitRegistrationFormRequestForm requestForm, Errors errors,
			@AccessingUser AuthorityEntity user) {
		if (errors.hasErrors()) {
			throw new BusinessFailureException(errors);
		}
		return service.updateForm(requestForm, user);
	}

	@DeleteMapping(path = "/delete/{formId}")
	@AllUsers
	public int deleteAURForm(@PathVariable Integer formId, @AccessingUser AuthorityEntity accessingUser) {
		return service.deleteAURForm(formId, accessingUser);
	}
}
