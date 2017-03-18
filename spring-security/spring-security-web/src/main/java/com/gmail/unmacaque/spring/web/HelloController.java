package com.gmail.unmacaque.spring.web;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	@GetMapping
	public String index() {
		return "index";
	}

	@GetMapping("/admin")
	public String admin(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		model.addAttribute("username", username);

		return "admin";
	}

	@GetMapping("/hello")
	public String hello(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		model.addAttribute("username", username);

		return "hello";
	}

	@GetMapping("/login")
	public String login(@RequestParam(required = false) String error,
			@RequestParam(required = false) String logout, ModelMap modelMap) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "forward:/hello";
		}

		modelMap.addAttribute("error", error != null);
		modelMap.addAttribute("logout", logout != null);

		return "login";
	}
}
