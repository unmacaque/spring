package com.gmail.unmacaque.springmvc.validation;

public class ValidationError {
	private final String field;
	private final String message;

	public ValidationError(String field) {
		this(field, "");
	}

	public ValidationError(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
}
