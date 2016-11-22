package com.gmail.unmacaque.spring.thymeleaf;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DateController {

	@RequestMapping(value = "/date")
	public String showDate() {
		return "date";
	}

	@ModelAttribute("date")
	public LocalDateTime date() {
		return LocalDateTime.now();
	}

}
