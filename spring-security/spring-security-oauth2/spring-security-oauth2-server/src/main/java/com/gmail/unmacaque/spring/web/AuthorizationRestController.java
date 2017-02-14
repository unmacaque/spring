package com.gmail.unmacaque.spring.web;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationRestController {

	@RequestMapping({ "/me", "/user" })
	public Map<String, String> user(Principal principal) {
		return Collections.singletonMap("name", principal.getName());
	}
}
