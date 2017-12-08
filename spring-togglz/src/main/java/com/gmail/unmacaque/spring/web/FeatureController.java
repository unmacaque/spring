package com.gmail.unmacaque.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gmail.unmacaque.spring.togglz.Features;

@Controller
public class FeatureController {
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/feature/{feature}")
	public String index(@PathVariable Features feature, ModelMap modelMap) {
		modelMap.addAttribute("feature", feature.name());
		return "feature";
	}
}
