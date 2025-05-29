package com.gmail.unmacaque.spring.security.oauth2.client.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	@Autowired
	private ResourceService resourceService;

	@GetMapping("/")
	public String index() {
		return resourceService.retrieveResource();
	}

}
