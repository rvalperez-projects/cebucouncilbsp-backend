/**
 *
 */
package com.cebucouncilbsp.backend.repository;

import java.util.List;

import com.cebucouncilbsp.backend.entity.AuthorityEntity;

/**
 * @author reneir.val.t.perez
 *
 */
public interface AuthorityRepository {

	int insertAuthority(AuthorityEntity authority);

	int updateAuthority(AuthorityEntity authority);

	List<AuthorityEntity> findAllAuthorizedUsers();

	AuthorityEntity findAuthUserByUserId(Integer userId);

	AuthorityEntity findAuthUserByToken(String token);

	AuthorityEntity findAuthUserByUsername(String username);

	AuthorityEntity findAuthUserByUsernamePassword(String username, String password);
}
