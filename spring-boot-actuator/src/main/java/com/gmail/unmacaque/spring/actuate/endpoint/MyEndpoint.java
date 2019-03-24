package com.gmail.unmacaque.spring.actuate.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
@Endpoint(id = "my-endpoint")
public class MyEndpoint {

	@ReadOperation
	public Map<String, Object> message() {
		return Collections.singletonMap("message", "Hello World");
	}

}
