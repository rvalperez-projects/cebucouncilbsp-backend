package com.cebucouncilbsp.backend.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cebucouncilbsp.backend.requestdto.LoginRequestForm;

/**
 * @author reneir.val.t.perez
 *
 */
@Component
public class LoginRequestFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginRequestForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasFieldErrors()) {
			return;
		}
	}
}
