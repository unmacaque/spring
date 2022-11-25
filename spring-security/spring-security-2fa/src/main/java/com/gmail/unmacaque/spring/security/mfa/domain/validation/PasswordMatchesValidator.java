package com.gmail.unmacaque.spring.security.mfa.domain.validation;

import com.gmail.unmacaque.spring.security.mfa.domain.RegisterUser;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegisterUser> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
	}

	@Override
	public boolean isValid(RegisterUser value, ConstraintValidatorContext context) {
		return value.getPassword().equals(value.getPasswordConfirm());
	}

}
