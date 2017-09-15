package com.gmail.unmacaque.spring.web;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(ModelMap modelMap, Principal principal) {
		modelMap.addAttribute("user", principal.getName());
		return "index";
	}

}
