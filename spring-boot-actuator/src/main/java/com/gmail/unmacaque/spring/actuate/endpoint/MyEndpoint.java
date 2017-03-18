package com.gmail.unmacaque.spring.actuate.endpoint;

import java.util.Collections;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.stereotype.Component;

@Component
public class MyEndpoint extends AbstractEndpoint<Map<String, Object>> {

	public MyEndpoint() {
		super("myEndpoint", false);
	}

	@Override
	public Map<String, Object> invoke() {
		return Collections.singletonMap("message", "Hello World");
	}

}
