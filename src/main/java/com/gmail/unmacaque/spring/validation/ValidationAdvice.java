package com.gmail.unmacaque.spring.validation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ValidationAdvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidationAdvice.class);
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public List<ValidationError> handleMethodArgumentNotValidException(HttpRequest request, MethodArgumentNotValidException exception) {
		BindingResult result = exception.getBindingResult();
		
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		for (FieldError error : result.getFieldErrors()) {
			LOGGER.error(error.getDefaultMessage());
			errors.add(new ValidationError(error.getField(), error.getDefaultMessage()));
		}
		
		return errors;
	}
	
	@ExceptionHandler
	@ResponseBody
	public String handleException(Exception e) {
		return e.getMessage();
	}
}
