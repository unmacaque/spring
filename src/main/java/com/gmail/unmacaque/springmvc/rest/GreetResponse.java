package com.gmail.unmacaque.springmvc.rest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GreetResponse {
	public GreetResponse() {
		this("anonymous");
	}
	
	public GreetResponse(String response) {
		this.response = response;
	}

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public Date getDate() {
		return date;
	}
	
	public String getResponse() {
		return response;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	@Override
	public String toString() {
		return this.response;
	}
	
	private Date date = new Date();
	private String response;
}
