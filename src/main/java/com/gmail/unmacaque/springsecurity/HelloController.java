package com.gmail.unmacaque.springsecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("index");
		model.addObject("role", "just a regular user");
		return model;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "redirect:admin";
	}
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public ModelAndView admin() {
		ModelAndView model = new ModelAndView("index");
		model.addObject("role", "an administrator");
		return model;
	}
}
