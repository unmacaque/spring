package com.gmail.unmacaque.spring.thirdparty.admin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "spring.boot.admin.client.enabled=false")
class ApplicationTest {

	@Test
	void contextLoads() {}

}
