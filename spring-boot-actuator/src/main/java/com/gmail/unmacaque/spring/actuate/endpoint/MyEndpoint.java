package com.gmail.unmacaque.spring.actuate.endpoint;

import java.util.Collections;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "my-endpoint")
public class MyEndpoint {

	@ReadOperation
	public Map<String, Object> message() {
		return Collections.singletonMap("message", "Hello World");
	}

}
