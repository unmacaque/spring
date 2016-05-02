package com.gmail.unmacaque.spring.boot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping(value = "/")
	public String hello() {
		return "index";
	}
}
