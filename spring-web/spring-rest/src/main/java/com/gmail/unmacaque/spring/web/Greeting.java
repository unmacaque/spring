package com.gmail.unmacaque.spring.web;

import java.time.LocalDateTime;

public class Greeting {

	private LocalDateTime date;
	private String message;

	public Greeting() {
		this("Hello World", LocalDateTime.now());
	}

	public Greeting(String message) {
		this(message, LocalDateTime.now());
	}

	public Greeting(String message, LocalDateTime date) {
		this.message = message;
		this.date = date;
	}

	@Override
	public String toString() {
		return message;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
