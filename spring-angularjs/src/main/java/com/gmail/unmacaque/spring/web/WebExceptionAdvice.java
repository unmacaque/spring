package com.gmail.unmacaque.spring.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class WebExceptionAdvice {

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<String> handleConstraintViolationException(ConstraintViolationException e) {
		return e.getConstraintViolations()
				.stream()
				.map(v -> v.getPropertyPath() + " " + v.getMessage())
				.collect(Collectors.toList());
	}

}
