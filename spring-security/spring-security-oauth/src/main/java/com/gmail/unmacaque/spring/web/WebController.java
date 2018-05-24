package com.gmail.unmacaque.spring.web;

import java.security.Principal;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	@GetMapping("/")
	public Object hello(Principal principal) {
		if (!(principal instanceof OAuth2AuthenticationToken)) {
			return principal.toString();
		}
		OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) principal;
		return token.getPrincipal().getAttributes();
	}

}
