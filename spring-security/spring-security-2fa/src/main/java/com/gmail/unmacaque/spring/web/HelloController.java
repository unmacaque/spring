package com.gmail.unmacaque.spring.web;

import java.security.Principal;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/hello")
	public String hello(ModelMap model, Principal principal) {
		model.addAttribute("username", principal.getName());
		return "hello";
	}

	@GetMapping("/login")
	public String login(Authentication authentication) {
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			return "forward:/hello";
		}
		return "login";
	}
}
