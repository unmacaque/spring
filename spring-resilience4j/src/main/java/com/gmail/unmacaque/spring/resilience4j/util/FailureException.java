package com.gmail.unmacaque.spring.resilience4j.util;

public class FailureException extends Exception {
	public FailureException() {
	}

	public FailureException(String message) {
		super(message);
	}

	public FailureException(String message, Throwable cause) {
		super(message, cause);
	}

	public FailureException(Throwable cause) {
		super(cause);
	}

	public FailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
