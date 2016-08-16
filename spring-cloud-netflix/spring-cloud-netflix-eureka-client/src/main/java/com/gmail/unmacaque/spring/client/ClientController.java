package com.gmail.unmacaque.spring.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ClientController {

	@Value("${spring.application.name}")
	private String applicationName;

	@RequestMapping(method = RequestMethod.GET)
	public String applicationName() {
		return applicationName;
	}
}
