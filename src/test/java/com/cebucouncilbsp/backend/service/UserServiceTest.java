/**
 *
 */
package com.cebucouncilbsp.backend.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

import com.cebucouncilbsp.backend.constant.AuthorityCategoryCode;
import com.cebucouncilbsp.backend.entity.AuthorityEntity;
import com.cebucouncilbsp.backend.entity.InstitutionEntity;
import com.cebucouncilbsp.backend.entity.UserEntity;
import com.cebucouncilbsp.backend.entity.UserProfileEntity;
import com.cebucouncilbsp.backend.repository.AuthorityRepository;
import com.cebucouncilbsp.backend.repository.InstitutionRepository;
import com.cebucouncilbsp.backend.repository.UserRepository;

/**
 * @author reneir.val.t.perez
 *
 */
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private InstitutionRepository institutionRepository;

	@Mock
	private AuthorityRepository authorityRepository;

	@InjectMocks
	private UserService service;

	@Before
	public void setUp() {
		// Create instance of class under test
		service = new UserService();

		// Set private fields of class under test
		Whitebox.setInternalState(this.service, "councilName", "Cebu Council");
		Whitebox.setInternalState(this.service, "councilAddress", "Address of Cebu Council");
		Whitebox.setInternalState(this.service, "councilContactNumber", "Contact Number of Cebu Council");

		// Initialize Mocked objects
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetUserByUserId_GENERAL_USER() {
		// Given
		// 1) Method parameter:
		Integer userId = 1;

		// 2) Expected return of mocked objects
		UserEntity user = new UserEntity();
		user.setInstitutionId(1);

		AuthorityEntity authority = new AuthorityEntity();
		authority.setRoleCode(AuthorityCategoryCode.GENERAL_USER.getCode());

		InstitutionEntity institution = new InstitutionEntity();

		// Mock
		PowerMockito.when(userRepository.findByUserId(ArgumentMatchers.anyInt())).thenReturn(user);
		PowerMockito.when(authorityRepository.findAuthUserByUserId(ArgumentMatchers.anyInt())).thenReturn(authority);
		PowerMockito.when(institutionRepository.findByInstitutionId(ArgumentMatchers.anyInt())).thenReturn(institution);

		// When method is called
		UserProfileEntity actual = this.service.getUserByUserId(userId);

		// Then:
		// 1) Assert actual values
		assertThat(actual, is(CoreMatchers.notNullValue()));
		assertThat(actual.getUserId(), is(userId));

		// 2) Verify that Mocked objects are called
		Mockito.verify(userRepository, times(1)).findByUserId(ArgumentMatchers.anyInt());
		Mockito.verify(authorityRepository, times(1)).findAuthUserByUserId(ArgumentMatchers.anyInt());
		Mockito.verify(institutionRepository, times(1)).findByInstitutionId(ArgumentMatchers.anyInt());
	}

	@Test
	public void testGetUserByUserId_COUNCIL() {
		// Given
		// 1) Method parameter:
		Integer userId = 1;

		// 2) Expected return of mocked objects
		UserEntity user = new UserEntity();
		user.setInstitutionId(1);

		AuthorityEntity authority = new AuthorityEntity();
		authority.setRoleCode(AuthorityCategoryCode.COUNCIL.getCode());

		InstitutionEntity institution = new InstitutionEntity();

		// Mock
		PowerMockito.when(userRepository.findByUserId(ArgumentMatchers.anyInt())).thenReturn(user);
		PowerMockito.when(authorityRepository.findAuthUserByUserId(ArgumentMatchers.anyInt())).thenReturn(authority);
		PowerMockito.when(institutionRepository.findByInstitutionId(ArgumentMatchers.anyInt())).thenReturn(institution);

		// When
		UserProfileEntity actual = this.service.getUserByUserId(userId);

		// Then:
		// 1) Assert actual values
		assertThat(actual, is(CoreMatchers.notNullValue()));
		assertThat(actual.getUserId(), is(userId));

		// 2) Verify that Mocked objects are called
		Mockito.verify(userRepository, times(1)).findByUserId(ArgumentMatchers.anyInt());
		Mockito.verify(authorityRepository, times(1)).findAuthUserByUserId(ArgumentMatchers.anyInt());
		Mockito.verify(institutionRepository, times(1)).findByInstitutionId(ArgumentMatchers.anyInt());
	}

}
