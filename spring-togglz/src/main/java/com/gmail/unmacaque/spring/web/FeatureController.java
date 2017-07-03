package com.gmail.unmacaque.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FeatureController {
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/feature/{feature}")
	public String index(@PathVariable String feature, ModelMap modelMap) {
		modelMap.addAttribute("feature", feature.toUpperCase());
		return "feature";
	}
}
