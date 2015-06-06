package com.gmail.unmacaque.spring.webflow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class OrderController {
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}
}
