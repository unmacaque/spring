package com.gmail.unmacaque.spring.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

	@Value("${unmacaque.spring.cloud.my-property}")
	private String myProperty;

	@GetMapping("/")
	public String myProperty() {
		return myProperty;
	}
}
