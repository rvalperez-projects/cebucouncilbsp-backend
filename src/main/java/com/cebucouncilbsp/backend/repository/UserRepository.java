/**
 *
 */
package com.cebucouncilbsp.backend.repository;

import java.util.List;

import com.cebucouncilbsp.backend.entity.LoginResultEntity;
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

	LoginResultEntity findByUsernamePassword(String username, String password);

	List<UserEntity> findByAreaInstitutionName(String area, Integer institutionId, String name);
}
