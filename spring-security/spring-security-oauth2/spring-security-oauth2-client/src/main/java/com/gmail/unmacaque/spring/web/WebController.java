package com.gmail.unmacaque.spring.web;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class WebController {

	@GetMapping("/")
	public Set<String> index(@RegisteredOAuth2AuthorizedClient("gitlab") OAuth2AuthorizedClient authorizedClient) {
		return authorizedClient.getAccessToken().getScopes();
	}

}
