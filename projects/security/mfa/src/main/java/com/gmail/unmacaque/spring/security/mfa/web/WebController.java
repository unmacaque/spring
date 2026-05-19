package com.gmail.unmacaque.spring.security.mfa.web;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	@GetMapping("/")
	public String index(Authentication authentication) {
		return "successfully authenticated as " + authentication.getName();
	}
}
