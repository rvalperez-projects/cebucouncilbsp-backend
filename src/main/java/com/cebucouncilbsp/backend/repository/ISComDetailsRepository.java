/**
 *
 */
package com.cebucouncilbsp.backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cebucouncilbsp.backend.entity.ISComDetailsEntity;

/**
 * @author reneir.val.t.perez
 *
 */
@Repository
public interface ISComDetailsRepository {

	int insertISComDetails(ISComDetailsEntity iSComDetails);

	int insertISComDetailsList(List<ISComDetailsEntity> iSComMembersList, Integer formId);

	int updateISComDetails(List<ISComDetailsEntity> iSComMembersList);

	List<ISComDetailsEntity> findAllISCommittee();

	List<ISComDetailsEntity> findByFormId(Integer formId);

	int deleteISComMembersByFormId(Integer formId);
}
