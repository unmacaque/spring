package com.gmail.unmacaque.spring.config;

import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@AutoConfigureWireMock
@Profile("!test")
public class WireMockConfiguration {}
