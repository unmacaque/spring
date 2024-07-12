package com.gmail.unmacaque.spring.security.oauth2.resourceserver.web;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	@GetMapping("/")
	public Object index(JwtAuthenticationToken token) {
		return token.getName();
	}

}
