package com.gmail.unmacaque.spring.resilience4j.util;

public class FailureException extends RuntimeException {

	public FailureException(String message) {
		super(message);
	}

}
