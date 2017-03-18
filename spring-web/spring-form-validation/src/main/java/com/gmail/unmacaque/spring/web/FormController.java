package com.gmail.unmacaque.spring.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	public String formPost(@Valid @ModelAttribute("data") FormData data, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "form";
		}
		model.addAttribute("formData", data);
		return "form";
	}
}
