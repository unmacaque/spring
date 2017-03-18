package com.gmail.unmacaque.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.gmail.unmacaque.spring.aop.HelloService;

public class AopApplicationRunner implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private HelloService helloService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info(helloService.sayHello());
	}

}
