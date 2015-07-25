package com.gmail.unmacaque.spring.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloAspect {

	private static final Logger logger = LogManager.getLogger();

	@Before("execution(* com.gmail.unmacaque.spring.aop.HelloService.sayHello())")
	public void beforeSayHello() {
		logger.info("I'm about to say hello");
	}

	@After("execution(* com.gmail.unmacaque.spring.aop.HelloService.sayHello())")
	public void afterSayHello() {
		logger.info("I have just said hello");
	}
}
