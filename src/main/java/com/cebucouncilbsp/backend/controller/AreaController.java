package com.cebucouncilbsp.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cebucouncilbsp.backend.annotation.AdminAndCouncilOnly;
import com.cebucouncilbsp.backend.entity.AreaEntity;
import com.cebucouncilbsp.backend.service.AreaService;

@RestController
@RequestMapping(value = "/area")
public class AreaController {

	@Autowired
	private AreaService service;

	@GetMapping(path = "/all")
	@AdminAndCouncilOnly
	public List<AreaEntity> getAllAreas() {
		return service.getAll();
	}

	@GetMapping(path = "/districts/institutions")
	@AdminAndCouncilOnly
	public Map<String, Map<String, Map<Integer, String>>> getAllAreasDistrictsInstitutions() {
		return service.getAllAreasDistrictsInstitutions();
	}

	@GetMapping(path = "/{areaCode}")
	@AdminAndCouncilOnly
	public List<AreaEntity> getAreaByCode(@PathVariable String areaCode) {
		return service.getAreaByCode(areaCode);
	}
}
