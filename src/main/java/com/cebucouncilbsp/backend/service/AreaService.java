/**
 *
 */
package com.cebucouncilbsp.backend.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cebucouncilbsp.backend.entity.AreaEntity;
import com.cebucouncilbsp.backend.entity.InstitutionEntity;
import com.cebucouncilbsp.backend.repository.AreaRepository;
import com.cebucouncilbsp.backend.repository.InstitutionRepository;

/**
 * @author reneir.val.t.perez
 *
 */
@Service
public class AreaService {

	@Autowired
	private AreaRepository areaRepository;
	@Autowired
	private InstitutionRepository institutionRepository;

	/**
	 *
	 * @return
	 */
	public List<AreaEntity> getAll() {
		return areaRepository.findAllAreas();
	}

	/**
	 *
	 * @return
	 */
	public Map<String, List<String>> getAreasAndDistricts() {
		Map<String, List<String>> result = new LinkedHashMap<>();
		List<AreaEntity> entities = areaRepository.findAllAreas();
		for (AreaEntity area : entities) {
			if (result.containsKey(area.getAreaCode())) {
				List<String> districts = result.get(area.getAreaCode());
				districts.add(area.getDistrictName());
				result.put(area.getAreaCode(), districts);
				continue;
			}
			List<String> districts = new ArrayList<>();
			districts.add(area.getDistrictName());
			result.put(area.getAreaCode(), districts);
		}
		return result;
	}

	/**
	 *
	 * @return
	 */
	@Transactional
	public Map<String, Map<String, Map<Integer, InstitutionEntity>>> getAllAreasDistrictsInstitutions() {

		Map<String, Map<String, Map<Integer, InstitutionEntity>>> areaMap = new LinkedHashMap<>();
		List<AreaEntity> areas = areaRepository.findDistinctAreas();
		if (areas == null) {
			return areaMap;
		}
		for (AreaEntity area : areas) {

			List<AreaEntity> districts = areaRepository.findByArea(area.getAreaCode());
			if (districts == null) {
				continue;
			}

			Map<String, Map<Integer, InstitutionEntity>> districtMap = new LinkedHashMap<>();
			for (AreaEntity district : districts) {

				List<InstitutionEntity> institutions = institutionRepository.findByDistrict(district.getDistrictName());
				if (institutions == null) {
					continue;
				}

				Map<Integer, InstitutionEntity> institutionMap = new LinkedHashMap<>();
				for (InstitutionEntity institution : institutions) {
					institutionMap.put(institution.getInstitutionId(), institution);
				}
				districtMap.put(district.getDistrictName(), institutionMap);
			}
			areaMap.put(area.getAreaCode(), districtMap);
		}
		return areaMap;
	}

	/**
	 *
	 * @param areaCode
	 * @return
	 */
	public List<AreaEntity> getAreaByCode(String areaCode) {
		return areaRepository.findByArea(areaCode);
	}

}
