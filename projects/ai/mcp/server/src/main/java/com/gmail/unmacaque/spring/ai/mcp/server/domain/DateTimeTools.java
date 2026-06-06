package com.gmail.unmacaque.spring.ai.mcp.server.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DateTimeTools {

	private static final Logger logger = LoggerFactory.getLogger(DateTimeTools.class);

	@McpTool(description = "Get the current date and time in the user's timezone")
	public String getCurrentDateTime() {
		logger.info("Received tool request: getCurrentDateTime");
		return LocalDateTime.now().toString();
	}

}
