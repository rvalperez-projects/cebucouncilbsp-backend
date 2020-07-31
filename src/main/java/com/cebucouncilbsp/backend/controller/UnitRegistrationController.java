package com.cebucouncilbsp.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public List<UnitRegistrationEntity> getAllUsers() {
		return service.getAll();
	}

	@GetMapping(path = "/{formId}")
	public UnitRegistrationEntity getUserByUserId(@PathVariable Integer formId) {
		return service.getByFormId(formId);
	}

	@PostMapping(path = "/submit")
	public int submit(@RequestBody @Valid UnitRegistrationFormRequestForm requestForm, Errors errors) {
		validator.validate(requestForm, errors);
		if (errors.hasErrors()) {
			throw new BusinessFailureException(errors);
		}
		return service.submit(requestForm);
	}

	@PostMapping(path = "/search")
	public List<UnitRegistrationSearchResultEntity> searchUsers(@RequestBody @Valid SearchRequestForm requestForm,
			Errors errors) {
		if (errors.hasErrors()) {
			throw new BusinessFailureException(errors);
		}
		return service.searchForms(requestForm);
	}

	@PutMapping(path = "/update-status")
	public UnitRegistrationEntity updateStatus(@PathVariable Integer formId, @PathVariable String statusCode) {
		return service.updateStatus(formId, statusCode);
	}

	@PutMapping(path = "/update")
	public UnitRegistrationEntity updateStatus(@RequestBody @Valid UnitRegistrationFormRequestForm requestForm,
			Errors errors) {
		validator.validate(requestForm, errors);
		if (errors.hasErrors()) {
			throw new BusinessFailureException(errors);
		}
		return service.updateForm(requestForm);
	}
}
