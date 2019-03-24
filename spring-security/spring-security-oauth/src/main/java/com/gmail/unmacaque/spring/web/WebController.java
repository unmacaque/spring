package com.gmail.unmacaque.spring.web;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class WebController {

	@GetMapping("/")
	public Object hello(Principal principal) {
		if (!(principal instanceof OAuth2AuthenticationToken)) {
			return principal.toString();
		}
		var token = (OAuth2AuthenticationToken) principal;
		return token.getPrincipal().getAttributes();
	}

}
