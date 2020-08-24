/**
 *
 */
package com.cebucouncilbsp.backend.repository;

import java.util.List;

import com.cebucouncilbsp.backend.entity.AreaEntity;

/**
 * @author reneir.val.t.perez
 *
 */
public interface AreaRepository {

	int insertArea(AreaEntity area);

	int updateAreaByAreaAndDistrict(AreaEntity area);

	List<AreaEntity> findAllAreas();

	List<AreaEntity> findDistinctAreas();

	List<AreaEntity> findByArea(String area);

	List<AreaEntity> findByDistrict(String district);

	AreaEntity findByAreaAndDistrict(String area, String district);
}
