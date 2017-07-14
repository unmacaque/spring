package com.gmail.unmacaque.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.gmail.unmacaque.spring.aop.HelloService;

public class AopApplicationRunner implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(AopApplicationRunner.class);

	private final HelloService helloService;

	public AopApplicationRunner(HelloService helloService) {
		this.helloService = helloService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info(helloService.sayHello());
	}

}
