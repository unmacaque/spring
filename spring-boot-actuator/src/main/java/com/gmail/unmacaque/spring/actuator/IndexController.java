package com.gmail.unmacaque.spring.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@Value("${application.welcome}")
	private String welcomeText;

	@GetMapping
	public String handleIndex() {
		return welcomeText;
	}
}
