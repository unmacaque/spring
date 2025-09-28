package com.gmail.unmacaque.spring.webflux.webclient.config;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "webclient")
@Validated
public record ClientProperties(@NotEmpty String baseUrl) {}
