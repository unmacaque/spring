package com.gmail.unmacaque.spring.thirdparty.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class Application {
	static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
