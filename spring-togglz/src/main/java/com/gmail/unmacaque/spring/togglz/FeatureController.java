package com.gmail.unmacaque.spring.togglz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FeatureController {
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/feature/{feature}")
	public String index(@PathVariable String feature, ModelMap modelMap) {
		modelMap.addAttribute("feature", feature.toUpperCase());
		return "feature";
	}
}
