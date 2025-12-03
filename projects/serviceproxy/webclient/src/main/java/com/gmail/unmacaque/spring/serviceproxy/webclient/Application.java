package com.gmail.unmacaque.spring.serviceproxy.webclient;

import com.gmail.unmacaque.spring.serviceproxy.webclient.domain.ReservationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.service.registry.HttpServiceGroup;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@ImportHttpServices(types = ReservationService.class, clientType = HttpServiceGroup.ClientType.WEB_CLIENT)
public class Application {
	static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
