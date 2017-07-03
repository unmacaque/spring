package com.gmail.unmacaque.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AngularController {
	@GetMapping("/")
	public String index() {
		return "index.html";
	}
}
