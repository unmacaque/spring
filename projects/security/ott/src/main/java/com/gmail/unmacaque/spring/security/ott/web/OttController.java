package com.gmail.unmacaque.spring.security.ott.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OttController {

	@GetMapping("/ott/sent")
	public String ottSent() {
		return "ott";
	}
}
