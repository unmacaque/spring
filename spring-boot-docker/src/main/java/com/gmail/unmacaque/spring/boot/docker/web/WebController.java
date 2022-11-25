package com.gmail.unmacaque.spring.boot.docker.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	@GetMapping("/")
	public String hello() {
		logger.info("Hello World");
		return "Hello World";
	}
}
