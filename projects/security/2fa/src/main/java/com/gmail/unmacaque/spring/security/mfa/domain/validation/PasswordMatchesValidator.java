package com.gmail.unmacaque.spring.security.mfa.domain.validation;

import com.gmail.unmacaque.spring.security.mfa.domain.RegisterUser;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegisterUser> {

	@Override
	public boolean isValid(RegisterUser value, ConstraintValidatorContext context) {
		return value.password().equals(value.passwordConfirm());
	}

}
