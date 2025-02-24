package com.gmail.unmacaque.spring.ai.domain;

import org.springframework.ai.tool.annotation.Tool;

import java.time.LocalDateTime;

public class DateTimeTools {

	@Tool(description = "Get the current date and time in the user's timezone")
	public String getCurrentDateTime() {
		return LocalDateTime.now().toString();
	}

}
