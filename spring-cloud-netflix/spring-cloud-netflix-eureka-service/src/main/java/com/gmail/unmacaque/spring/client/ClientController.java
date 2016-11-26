package com.gmail.unmacaque.spring.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

	@Value("${spring.application.name}")
	private String applicationName;

	@GetMapping("/")
	public String applicationName() {
		return applicationName;
	}
}
