package com.gmail.unmacaque.spring.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class WebController {

	@GetMapping("/")
	public Mono<String> index(@AuthenticationPrincipal OidcUser user) {
		return Mono.just(user.getSubject());
	}

}
