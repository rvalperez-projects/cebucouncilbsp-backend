package com.cebucouncilbsp.backend.advice;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cebucouncilbsp.backend.ResponseBodyWrapper;
import com.cebucouncilbsp.backend.constant.ResponseStatusCode;
import com.cebucouncilbsp.backend.exception.BusinessFailureException;

/**
 * @author reneir.val.t.perez
 *
 */
@ControllerAdvice
@Order(0)
public class ValidationErrorAdvice extends ResponseEntityExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationErrorAdvice.class);
	private static final String NO_MESSAGE_FOUND_ERROR = "No message found";

	@Autowired
	MessageSource messageSource;

	/**
	 * Handles {@link BusinessFailureException} and creates an object that shows a
	 * Validation error.
	 *
	 * @param exception The exception thrown: {@link BusinessFailureException}.
	 * @return A {@link ResponseBodyWrapper} that contains the Validation error.
	 */
	@ExceptionHandler(BusinessFailureException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseBodyWrapper handleValidationError(BusinessFailureException exception) {
		LOGGER.info(MessageFormat.format("BusinessFailureException has occurred. {0}", exception.getMessage()));
		Errors errors = exception.getErrors();

		List<String> errorMessagesList = new ArrayList<>();
		if (errors != null) {
			setValidationErrorMessages(errorMessagesList, errors);
		}
		return new ResponseBodyWrapper(ResponseStatusCode.VALIDATION_FAILURE.getCode(), null, errorMessagesList);
	}

	/**
	 * Set Validation Error Messages and populate to errorMessagesList.
	 *
	 * @param errorMessagesList List to populate the messages.
	 * @param errors            Validation errors.
	 */
	private void setValidationErrorMessages(List<String> errorMessagesList, Errors errors) {

		List<FieldError> fieldErrors = errors.getFieldErrors();
		for (FieldError fe : fieldErrors) {
			String message = null;
			try {
				message = messageSource.getMessage(fe.getCode(), fe.getArguments(), null);
			} catch (NoSuchMessageException e) {
				// Intended to be empty
				LOGGER.debug(NO_MESSAGE_FOUND_ERROR, e);
				try {
					message = messageSource.getMessage(fe.getDefaultMessage(), null, null);
				} catch (NoSuchMessageException ex2) {
					// Intended to be empty
					LOGGER.debug(NO_MESSAGE_FOUND_ERROR, ex2);
				}
			}
			if (message == null) {
				message = fe.getDefaultMessage();
			}
			errorMessagesList.add(MessageFormat.format("[{0}] {1}", fe.getField(), message));
		}

		List<ObjectError> globalErrors = errors.getGlobalErrors();
		for (ObjectError oe : globalErrors) {
			String message = null;
			try {
				message = messageSource.getMessage(oe.getCode(), oe.getArguments(), null);
			} catch (NoSuchMessageException e) {
				LOGGER.debug(NO_MESSAGE_FOUND_ERROR, e);
			}
			if (message == null) {
				message = oe.getDefaultMessage();
			}
			errorMessagesList.add(message);
		}
	}
}
