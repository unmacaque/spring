package com.gmail.unmacaque.spring;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		model.addAttribute("username", username);

		return new ModelAndView("admin");
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ModelAndView hello(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		model.addAttribute("username", username);

		return new ModelAndView("hello");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(required = false) String error) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return new ModelAndView("forward:/hello");
		}

		ModelAndView model = new ModelAndView("login");

		if (error != null) {
			model.addObject("error", "Invalid username and password");
		}

		return model;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout() {
		return new ModelAndView("logout");
	}
}
