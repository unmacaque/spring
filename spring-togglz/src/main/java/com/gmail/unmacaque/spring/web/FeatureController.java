package com.gmail.unmacaque.spring.web;

import com.gmail.unmacaque.spring.togglz.Features;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeatureController {
	@GetMapping("/feature/{feature}")
	public String index(@PathVariable Features feature) {
		return feature.isActive() ? "enabled" : "disabled";
	}
}
