package com.gmail.unmacaque.spring.security.web.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HelloController {

	@GetMapping("/admin")
	public String admin(ModelMap model, Principal principal) {
		model.addAttribute("username", principal.getName());
		return "admin";
	}

	@GetMapping("/hello")
	public String hello(ModelMap model, Principal principal) {
		model.addAttribute("username", principal.getName());
		return "hello";
	}
}
