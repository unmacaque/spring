package com.gmail.unmacaque.spring.grpc.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ApplicationTest {

	@MockBean
	private ApplicationRunner applicationRunner;

	@Test
	void contextLoads() {
	}

}
