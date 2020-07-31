package com.cebucouncilbsp.backend.validator;

import org.springframework.validation.Errors;

public interface BackendValidator<T> {
    void validate(T obj, Errors errors);
}
