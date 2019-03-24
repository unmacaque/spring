package com.gmail.unmacaque.spring.web;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

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
