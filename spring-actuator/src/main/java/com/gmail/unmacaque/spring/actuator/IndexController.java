package com.gmail.unmacaque.spring.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class IndexController {

	@Value("${application.welcome}")
	private String welcomeText;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String handleIndex() {
		return welcomeText;
	}
}
