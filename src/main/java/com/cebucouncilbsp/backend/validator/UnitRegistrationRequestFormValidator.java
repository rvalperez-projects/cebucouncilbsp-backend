package com.cebucouncilbsp.backend.validator;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cebucouncilbsp.backend.constant.ScoutingSectionCode;
import com.cebucouncilbsp.backend.entity.UnitNumberEntity;
import com.cebucouncilbsp.backend.repository.UnitNumberRepository;
import com.cebucouncilbsp.backend.requestdto.UnitRegistrationFormRequestForm;

/**
 * @author reneir.val.t.perez
 *
 */
@Component
public class UnitRegistrationRequestFormValidator implements Validator {

	@Autowired
	private UnitNumberRepository unitNumberRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return UnitRegistrationFormRequestForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasFieldErrors()) {
			return;
		}

		UnitRegistrationFormRequestForm obj = (UnitRegistrationFormRequestForm) target;

		// Unit Number
		if (null != obj.getUnitNumber()) {
			UnitNumberEntity unitNumberEntity = unitNumberRepository.findByUnitNumber(obj.getUnitNumber());
			if (null == unitNumberEntity) {
				errors.reject("backend.error.unitNumber.NotFound", new Object[] { obj.getUnitNumber() }, null);
				return;
			}

			// Unit Number already used
			if (unitNumberEntity.getLastUsedYear() == LocalDate.now().getYear()) {
				errors.reject("backend.aur.submit.unitNumber.NotAvailable", new Object[] { obj.getUnitNumber() }, null);
			}

			// Unit Number and Section do not match
			if (!unitNumberEntity.getSectionCode().equals(obj.getSectionCode())) {
				errors.reject("backend.aur.submit.unitNumber.SectionNotMatched", new Object[] { obj.getUnitNumber(),
						ScoutingSectionCode.get(obj.getSectionCode()).getSectionName() }, null);
			}
		}
	}
}
