/**
 *
 */
package com.cebucouncilbsp.backend.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cebucouncilbsp.backend.constant.AreaCode;
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
	private static final Logger LOGGER = LoggerFactory.getLogger(AreaService.class);

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
	@Transactional
	public Map<String, Map<String, Map<Integer, String>>> getAllAreasDistrictsInstitutions() {

		Map<String, Map<String, Map<Integer, String>>> areaMap = new LinkedHashMap<>();
		List<AreaEntity> areas = areaRepository.findDistinctAreas();
		if (areas == null) {
			return areaMap;
		}
		for (AreaEntity area : areas) {

			List<AreaEntity> districts = areaRepository.findByArea(area.getAreaCode());
			if (districts == null) {
				continue;
			}

			Map<String, Map<Integer, String>> districtMap = new LinkedHashMap<>();
			for (AreaEntity district : districts) {

				List<InstitutionEntity> institutions = institutionRepository.findByDistrict(district.getDistrictName());
				if (institutions == null) {
					continue;
				}

				Map<Integer, String> institutionMap = new LinkedHashMap<>();
				for (InstitutionEntity institution : institutions) {
					institutionMap.put(institution.getInstitutionId(), institution.getInstitutionName());
				}
				districtMap.put(district.getDistrictName(), institutionMap);
			}
			areaMap.put(AreaCode.get(area.getAreaCode()).name(), districtMap);
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
