package com.gmail.unmacaque.spring.web;

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
