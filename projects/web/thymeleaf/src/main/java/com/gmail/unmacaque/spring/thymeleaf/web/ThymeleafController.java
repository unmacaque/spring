package com.gmail.unmacaque.spring.thymeleaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Collection;
import java.util.Set;

import static java.util.function.Predicate.not;

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
		final Set<RequestMappingInfo> mappingInfoSet = handlerMapping.getHandlerMethods().keySet();
		return mappingInfoSet
				.stream()
				.flatMap(info -> info.getDirectPaths().stream())
				.filter(not(s -> s.equals("/error")))
				.sorted()
				.toList();
	}
}
