package com.gmail.unmacaque.spring.security.x509.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index(Principal principal) {
		return "Hello, " + principal.getName();
	}

}
