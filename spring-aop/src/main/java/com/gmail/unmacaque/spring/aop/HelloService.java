package com.gmail.unmacaque.spring.aop;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
	public String sayHello() {
		return "Hello World";
	}
}
