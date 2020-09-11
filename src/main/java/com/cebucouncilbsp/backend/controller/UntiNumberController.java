package com.cebucouncilbsp.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cebucouncilbsp.backend.annotation.AccessingUser;
import com.cebucouncilbsp.backend.annotation.AdminAndCouncilOnly;
import com.cebucouncilbsp.backend.entity.AuthorityEntity;
import com.cebucouncilbsp.backend.entity.UnitNumberEntity;
import com.cebucouncilbsp.backend.entity.UnitNumberSearchResultEntity;
import com.cebucouncilbsp.backend.exception.BusinessFailureException;
import com.cebucouncilbsp.backend.service.UnitNumberService;

@RestController
@RequestMapping(value = "/unitNumber")
public class UntiNumberController {

	@Autowired
	private UnitNumberService service;

	@GetMapping(path = "/all")
	@AdminAndCouncilOnly
	public List<UnitNumberEntity> getAll() {
		return service.getAll();
	}

	@GetMapping(path = "/{unitNumber}")
	@AdminAndCouncilOnly
	public UnitNumberEntity getUnitNumber(@PathVariable String unitNumber) {
		return service.getUnitNumber(unitNumber);
	}

	@GetMapping(path = "/institution/{institutionId}")
	@AdminAndCouncilOnly
	public List<UnitNumberEntity> getUnitNumbersByInstitutionId(@PathVariable Integer institutionId) {
		return service.getUnitNumbersByInstitutionId(institutionId);
	}

	@GetMapping(path = "/search/{institutionId}")
	@AdminAndCouncilOnly
	public UnitNumberSearchResultEntity searchUnitNumbers(@PathVariable Integer institutionId) {
		return service.searchUnitNumbers(institutionId);
	}

	@PostMapping(path = "/new")
	@AdminAndCouncilOnly
	public int createNewUnitNumber(@RequestBody UnitNumberEntity requestForm, Errors errors,
			@AccessingUser AuthorityEntity user) {
		if (errors.hasErrors()) {
			throw new BusinessFailureException(errors);
		}
		return service.createNewUnitNumber(requestForm, user);
	}

	@PutMapping(path = "/update")
	@AdminAndCouncilOnly
	public int updateUnitNumberInstitution(@RequestBody List<UnitNumberEntity> requestForm, Errors errors,
			@AccessingUser AuthorityEntity user) {
		if (errors.hasErrors()) {
			throw new BusinessFailureException(errors);
		}
		return service.updateUnitNumberInstitution(requestForm, user);
	}
}
