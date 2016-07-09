package com.gmail.unmacaque.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloAspect {

	private static final Logger logger = LoggerFactory.getLogger(HelloAspect.class);

	@Before("execution(* com.gmail.unmacaque.spring.aop.HelloService.sayHello())")
	public void beforeSayHello() {
		logger.info("I'm about to say hello");
	}

	@After("execution(* com.gmail.unmacaque.spring.aop.HelloService.sayHello())")
	public void afterSayHello() {
		logger.info("I have just said hello");
	}
}
