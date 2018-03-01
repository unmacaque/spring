package com.gmail.unmacaque.spring.config;

import java.util.EventListener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.tomakehurst.wiremock.servlet.WireMockHandlerDispatchingServlet;
import com.github.tomakehurst.wiremock.servlet.WireMockWebContextListener;

@Configuration
public class ServletConfiguration {

	@Bean
	public ServletListenerRegistrationBean<EventListener> servletListenerRegistrationBean() {
		return new ServletListenerRegistrationBean<>(new WireMockWebContextListener());
	}

	private WireMockHandlerDispatchingServlet wireMockServlet() {
		return new WireMockHandlerDispatchingServlet();
	}

	@Bean
	public ServletRegistrationBean<WireMockHandlerDispatchingServlet> wireMockServletRegistrationBean() {
		ServletRegistrationBean<WireMockHandlerDispatchingServlet> registration = new ServletRegistrationBean<>(
				wireMockServlet(), "/*");
		registration.addInitParameter("RequestHandlerClass", "com.github.tomakehurst.wiremock.http.StubRequestHandler");
		return registration;
	}
}
