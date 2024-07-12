package com.gmail.unmacaque.spring.boot.domain;

import java.util.List;

public record MyBeanImpl(List<Object> objects) implements MyBean {}
