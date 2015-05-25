package com.gmail.unmacaque.spring.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
public class HelloController {
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		model.addAttribute("username", username);

		return "admin";
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		model.addAttribute("username", username);

		return "hello";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(required = false) String error,
			@RequestParam(required = false) String logout, ModelMap modelMap) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "forward:/hello";
		}

		if (error != null) {
			modelMap.addAttribute("error", "Invalid username and password");
		}

		if (logout != null) {
			modelMap.addAttribute("logout", "You have successfully logged out");
		}

		return "login";
	}
}
