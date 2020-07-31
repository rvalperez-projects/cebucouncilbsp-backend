/**
 *
 */
package com.cebucouncilbsp.backend.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cebucouncilbsp.backend.entity.InstitutionEntity;
import com.cebucouncilbsp.backend.entity.LoginResultEntity;
import com.cebucouncilbsp.backend.entity.UserEntity;

/**
 * @author reneir.val.t.perez
 *
 */
public interface UserRepository {

	int insertUser(@Param("user") UserEntity user, @Param("institution") InstitutionEntity institution);

	int updateUser(UserEntity user);

	List<UserEntity> findAllUsers();

	UserEntity findByUserId(Integer userId);

	LoginResultEntity findByUsernamePassword(String username, String password);

	List<UserEntity> findByAreaDistrictInstitutionName(String area, String district, Integer institutionId,
			String name);
}
