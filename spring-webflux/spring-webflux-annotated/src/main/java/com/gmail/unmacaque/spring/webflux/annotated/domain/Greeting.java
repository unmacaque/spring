package com.gmail.unmacaque.spring.webflux.annotated.domain;

import java.time.Instant;

public record Greeting(String name, Instant time) {}
