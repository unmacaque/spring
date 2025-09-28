package com.gmail.unmacaque.spring.serviceproxy.webclient.config;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("proxy")
@Validated
public record ProxyProperties(@NotEmpty String baseUrl) {}
