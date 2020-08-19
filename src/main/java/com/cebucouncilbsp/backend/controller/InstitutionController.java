package com.cebucouncilbsp.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cebucouncilbsp.backend.annotation.AdminUserOnly;
import com.cebucouncilbsp.backend.annotation.AllUsers;
import com.cebucouncilbsp.backend.entity.InstitutionEntity;
import com.cebucouncilbsp.backend.service.InstitutionService;

@RestController
@RequestMapping(value = "/institution")
public class InstitutionController {

	@Autowired
	private InstitutionService service;

	@GetMapping(path = "/all")
	@AdminUserOnly
	public List<InstitutionEntity> getAllUsers() {
		return service.getAll();
	}

	@GetMapping(path = "/{institutionId}")
	@AllUsers
	public InstitutionEntity getInstitutionId(@PathVariable Integer institutionId) {
		return service.getIntitutionById(institutionId);
	}
}
