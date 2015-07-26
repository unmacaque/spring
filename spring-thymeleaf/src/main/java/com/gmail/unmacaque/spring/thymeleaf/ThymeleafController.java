package com.gmail.unmacaque.spring.thymeleaf;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
public class ThymeleafController {

	@Autowired
	private RequestMappingHandlerMapping handlerMapping;

	@RequestMapping(value = "/")
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
