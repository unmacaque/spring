package com.gmail.unmacaque.spring.security.ott.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	@GetMapping("/")
	public String welcome(ModelMap modelMap, @AuthenticationPrincipal UserDetails userDetails) {
		modelMap.addAttribute("username", userDetails.getUsername());
		return "welcome";
	}

}
