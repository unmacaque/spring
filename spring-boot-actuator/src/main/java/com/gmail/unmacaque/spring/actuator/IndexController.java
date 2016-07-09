package com.gmail.unmacaque.spring.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@Value("${application.welcome}")
	private String welcomeText;

	@RequestMapping(method = RequestMethod.GET)
	public String handleIndex() {
		return welcomeText;
	}
}
