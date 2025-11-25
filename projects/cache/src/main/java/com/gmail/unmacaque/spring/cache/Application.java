package com.gmail.unmacaque.spring.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Application {
	static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
