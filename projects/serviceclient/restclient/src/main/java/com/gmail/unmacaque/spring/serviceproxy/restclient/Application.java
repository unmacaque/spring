package com.gmail.unmacaque.spring.serviceproxy.restclient;

import com.gmail.unmacaque.spring.serviceproxy.restclient.domain.ReservationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@ImportHttpServices(types = ReservationService.class)
public class Application {
	static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
