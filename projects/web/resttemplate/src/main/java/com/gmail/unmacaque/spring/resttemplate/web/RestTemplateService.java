package com.gmail.unmacaque.spring.resttemplate.web;

import com.gmail.unmacaque.spring.resttemplate.domain.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

	private static final Logger logger = LoggerFactory.getLogger(RestTemplateService.class);

	private final RestTemplate restTemplate;

	public RestTemplateService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public String doCall() {
		final var bundle = restTemplate.getForObject("http://localhost:8888/", Bundle.class);
		if (bundle == null) {
			return null;
		}
		logger.info(bundle.content());
		return bundle.content();
	}
}
