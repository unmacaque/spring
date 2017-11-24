package com.gmail.unmacaque.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FormController {

	@ModelAttribute("data")
	public FormData getFormData() {
		return new FormData();
	}

	@GetMapping
	public String formGet() {
		return "form";
	}

	@PostMapping
	public String formPost(@Validated @ModelAttribute("data") FormData data, BindingResult result, ModelMap modelMap) {
		if (result.hasErrors()) {
			return "form";
		}
		modelMap.addAttribute("formData", data);
		return "form";
	}
}
