package com.gmail.unmacaque.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.gmail.unmacaque.spring.domain.Bundle;

@Component
@Profile("!test")
public class RestTemplateApplicationRunner implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Override
	public void run(ApplicationArguments args) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		Bundle bundle = restTemplate.getForObject("http://localhost:8080/", Bundle.class);
		logger.info(bundle.getContent());
	}
}
