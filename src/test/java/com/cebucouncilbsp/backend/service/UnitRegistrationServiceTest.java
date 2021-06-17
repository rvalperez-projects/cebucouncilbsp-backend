/**
 *
 */
package com.cebucouncilbsp.backend.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

import com.cebucouncilbsp.backend.entity.ISComDetailsEntity;
import com.cebucouncilbsp.backend.entity.MemberDetailsEntity;
import com.cebucouncilbsp.backend.entity.UnitRegistrationEntity;
import com.cebucouncilbsp.backend.exception.BusinessFailureException;
import com.cebucouncilbsp.backend.repository.ISComDetailsRepository;
import com.cebucouncilbsp.backend.repository.MemberDetailsRepository;
import com.cebucouncilbsp.backend.repository.UnitRegistrationRepository;

/**
 * @author reneir.val.t.perez
 *
 */
//@RunWith(PowerMockRunner.class)
public class UnitRegistrationServiceTest {

	@Mock
	private UnitRegistrationRepository unitRegistrationRepository;

	@Mock
	private ISComDetailsRepository iSComDetailsRepository;

	@Mock
	private MemberDetailsRepository memberDetailsRepository;

	@InjectMocks
	private UnitRegistrationService service;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new UnitRegistrationService();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAll_Normal() {
		// Given
		List<UnitRegistrationEntity> expected = new ArrayList<>();
		PowerMockito.when(unitRegistrationRepository.findAllUnitRegistrations()).thenReturn(expected);

		// When
		List<UnitRegistrationEntity> actual = this.service.getAll();

		// Then (Assert)
		assertThat(actual, is(expected));
	}

	@Test
	public void getByFormId_Normal() {
		// Given
		UnitRegistrationEntity expectedUnitRegistration = new UnitRegistrationEntity();
		List<ISComDetailsEntity> expectedIsComDetailsList = new ArrayList<>();
		List<MemberDetailsEntity> expectedMemberDetailsList = new ArrayList<>();

		PowerMockito.when(unitRegistrationRepository.findByFormId(Matchers.anyInt()))
				.thenReturn(expectedUnitRegistration);
		PowerMockito.when(iSComDetailsRepository.findByFormId(Matchers.anyInt())).thenReturn(expectedIsComDetailsList);
		PowerMockito.when(memberDetailsRepository.findByFormId(Matchers.anyInt()))
				.thenReturn(expectedMemberDetailsList);

		// When
		UnitRegistrationEntity actual = this.service.getByFormId(1);

		// Then
		assertThat(actual, is(expectedUnitRegistration));
	}

	@Test(expected = BusinessFailureException.class)
	public void getByFormId_Exception() {
		// Given
		UnitRegistrationEntity expectedUnitRegistration = null;

		PowerMockito.when(unitRegistrationRepository.findByFormId(Matchers.anyInt()))
				.thenReturn(expectedUnitRegistration);

		// When
		UnitRegistrationEntity actual = this.service.getByFormId(1);

	}

}
