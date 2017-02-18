package com.gmail.unmacaque.spring.security;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index(Principal principal) {
		return "Hello, " + principal.getName();
	}

}
