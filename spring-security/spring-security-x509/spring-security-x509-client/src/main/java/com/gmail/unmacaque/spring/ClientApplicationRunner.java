package com.gmail.unmacaque.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.client.RestTemplate;

public class ClientApplicationRunner implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(ClientApplicationRunner.class);

	private final RestTemplate restTemplate;

	public ClientApplicationRunner(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String responseText = restTemplate.getForObject("https://localhost:8443", String.class);
		logger.info(responseText);
	}
}
