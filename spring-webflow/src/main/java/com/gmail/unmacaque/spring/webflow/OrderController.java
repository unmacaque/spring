package com.gmail.unmacaque.spring.webflow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

	@GetMapping
	public String index() {
		return "index";
	}

}
