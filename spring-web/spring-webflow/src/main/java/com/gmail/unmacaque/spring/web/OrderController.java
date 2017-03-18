package com.gmail.unmacaque.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class OrderController {

	@GetMapping
	public String index() {
		return "index";
	}

}
