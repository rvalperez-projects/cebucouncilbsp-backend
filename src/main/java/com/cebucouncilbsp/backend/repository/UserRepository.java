/**
 *
 */
package com.cebucouncilbsp.backend.repository;

import java.util.List;

import com.cebucouncilbsp.backend.entity.UserEntity;

/**
 * @author reneir.val.t.perez
 *
 */
public interface UserRepository {

	int insertUser(UserEntity user);

	int updateUser(UserEntity user);

	List<UserEntity> findAllUsers();

	UserEntity findByUserId(Integer userId);

	List<UserEntity> findByAreaDistrictInstitutionName(String area, String district, Integer institutionId,
			String name);
}
