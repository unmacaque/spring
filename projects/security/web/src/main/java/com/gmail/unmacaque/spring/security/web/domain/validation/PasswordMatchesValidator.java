package com.gmail.unmacaque.spring.security.web.domain.validation;

import com.gmail.unmacaque.spring.security.web.domain.RegisterUser;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegisterUser> {

	private PasswordMatches annotation;

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
		this.annotation = constraintAnnotation;
	}

	@Override
	public boolean isValid(RegisterUser value, ConstraintValidatorContext context) {
		final boolean valid = value.password().equals(value.passwordConfirm());
		if (valid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(annotation.message()).addConstraintViolation();
		}
		return valid;
	}

}
