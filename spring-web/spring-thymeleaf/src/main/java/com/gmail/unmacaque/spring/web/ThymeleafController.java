package com.gmail.unmacaque.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

@Controller
public class ThymeleafController {

	private final RequestMappingHandlerMapping handlerMapping;

	public ThymeleafController(RequestMappingHandlerMapping handlerMapping) {
		this.handlerMapping = handlerMapping;
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@ModelAttribute("mappings")
	public Collection<String> mappings() {
		Set<String> mappings = new TreeSet<>();
		Set<RequestMappingInfo> mappingInfoSet = handlerMapping.getHandlerMethods().keySet();
		mappingInfoSet.forEach(mapping -> mappings.addAll(mapping.getPatternsCondition().getPatterns()));
		return mappings;
	}
}
