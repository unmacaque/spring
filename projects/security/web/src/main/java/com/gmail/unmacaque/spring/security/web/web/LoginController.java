package com.gmail.unmacaque.spring.security.web.web;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(Authentication authentication) {
		if (authentication != null) {
			return "forward:/hello";
		}
		return "login";
	}
}
