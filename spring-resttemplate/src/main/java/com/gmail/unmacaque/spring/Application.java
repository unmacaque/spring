package com.gmail.unmacaque.spring;

import java.util.EventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.github.tomakehurst.wiremock.servlet.WireMockHandlerDispatchingServlet;
import com.github.tomakehurst.wiremock.servlet.WireMockWebContextListener;
import com.gmail.unmacaque.spring.resttemplate.Bundle;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public WireMockHandlerDispatchingServlet wireMockServlet() {
		return new WireMockHandlerDispatchingServlet();
	}

	@Bean
	public ServletListenerRegistrationBean<EventListener> servletListenerRegistrationBean() {
		return new ServletListenerRegistrationBean<>(new WireMockWebContextListener());
	}

	@Bean
	public ServletRegistrationBean wireMockRegistrationBean() {
		ServletRegistrationBean registration = new ServletRegistrationBean(wireMockServlet(), "/*");
		registration.addInitParameter("RequestHandlerClass", "com.github.tomakehurst.wiremock.http.StubRequestHandler");
		return registration;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		context.close();
	}

	@Override
	public void run(String... args) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		Bundle bundle = restTemplate.getForObject("http://localhost:8080/", Bundle.class);
		logger.info(bundle.getContent());
	}
}
