package com.gmail.unmacaque.spring.thymeleaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;

@Controller
public class DateController {

	@GetMapping("/date")
	public String showDate() {
		return "date";
	}

	@ModelAttribute("date")
	public LocalDateTime date() {
		return LocalDateTime.now();
	}

}
