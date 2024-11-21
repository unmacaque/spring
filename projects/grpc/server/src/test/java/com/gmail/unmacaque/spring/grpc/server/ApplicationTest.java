package com.gmail.unmacaque.spring.grpc.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class ApplicationTest {

	@MockitoBean
	private ApplicationRunner applicationRunner;

	@Test
	void contextLoads() {
	}

}
