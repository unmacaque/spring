package com.gmail.unmacaque.spring.rest;

import java.time.LocalDateTime;

public class GreetResponse {

	private LocalDateTime date = LocalDateTime.now();
	private String response;

	public GreetResponse() {
		this("anonymous");
	}
	
	public GreetResponse(String response) {
		this.response = response;
	}

	public LocalDateTime getDate() {
		return date;
	}
	
	public String getResponse() {
		return response;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	@Override
	public String toString() {
		return this.response;
	}
}
