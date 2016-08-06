package com.gmail.unmacaque.spring.validation;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class FormController {

	@ModelAttribute("data")
	public FormData getFormData() {
		return new FormData();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String formGet() {
		return "form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String formPost(@Valid @ModelAttribute("data") FormData data, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "form";
		}
		model.addAttribute("formData", data);
		return "form";
	}
}
