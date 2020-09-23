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

import com.cebucouncilbsp.backend.entity.AreaDistrictsEntity;
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
	public List<AreaDistrictsEntity> getAreasAndDistricts() {
		List<AreaDistrictsEntity> result = new ArrayList<>();
		List<AreaEntity> entities = areaRepository.findAllAreas();
		for (AreaEntity area : entities) {
			AreaDistrictsEntity district = new AreaDistrictsEntity();
			district.setArea(area.getAreaCode());
			district.setDistrict(area.getDistrictName());
			result.add(district);
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
