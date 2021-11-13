package com.gmail.unmacaque.spring.web;

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
	public String hello(ModelMap model, Authentication authentication) {
		model.addAttribute("username", authentication.getName());
		return "hello";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
