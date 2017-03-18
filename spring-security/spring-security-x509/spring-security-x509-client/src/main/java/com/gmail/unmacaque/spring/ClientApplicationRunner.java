package com.gmail.unmacaque.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.client.RestTemplate;

public class ClientApplicationRunner implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		String responseText = restTemplate.getForObject("https://localhost:8443", String.class);
		logger.info(responseText);
	}
}
