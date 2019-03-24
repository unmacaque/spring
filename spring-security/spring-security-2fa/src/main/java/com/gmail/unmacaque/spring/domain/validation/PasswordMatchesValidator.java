package com.gmail.unmacaque.spring.domain.validation;

import com.gmail.unmacaque.spring.domain.RegisterUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegisterUser> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
	}

	@Override
	public boolean isValid(RegisterUser value, ConstraintValidatorContext context) {
		return value.getPassword().equals(value.getPasswordConfirm());
	}

}
