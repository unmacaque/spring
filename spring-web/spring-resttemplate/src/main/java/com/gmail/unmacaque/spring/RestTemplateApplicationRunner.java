package com.gmail.unmacaque.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.gmail.unmacaque.spring.domain.Bundle;

@Component
@Profile("!test")
public class RestTemplateApplicationRunner implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(RestTemplateApplicationRunner.class);

	@Override
	public void run(ApplicationArguments args) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		try {
			Bundle bundle = restTemplate.getForObject("http://localhost:8888/", Bundle.class);
			if (bundle != null) {
				logger.info(bundle.getContent());
			}
		} catch (RestClientException e) {
			logger.error(e.toString(), e);
		}
	}
}
