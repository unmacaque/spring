package com.gmail.unmacaque.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gmail.unmacaque.spring.aop.HelloService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private HelloService helloService;

	@Override
	public void run(String... args) {
		logger.info(helloService.sayHello());
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
