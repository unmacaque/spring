package com.gmail.unmacaque.spring.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ApiController {

	@GetMapping("/api")
	public String index(Principal principal) {
		return principal.getName();
	}

}
