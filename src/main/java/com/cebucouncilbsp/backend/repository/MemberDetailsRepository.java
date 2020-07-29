/**
 *
 */
package com.cebucouncilbsp.backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cebucouncilbsp.backend.entity.MemberDetailsEntity;

/**
 * @author reneir.val.t.perez
 *
 */
@Repository
public interface MemberDetailsRepository {

	int insertMemberDetails(MemberDetailsEntity memberDetails);

	int updateMemberDetails(MemberDetailsEntity memberDetails);

	List<MemberDetailsEntity> findAllUnitMembers();

	List<MemberDetailsEntity> findByFormId(Integer formId);
}
