package com.gmail.unmacaque.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(ModelMap map, Principal principal) {
		map.addAttribute("username", principal.getName());
		return "index";
	}

}
