package com.gmail.unmacaque.spring.testcontainers;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("testcontainers")
@Tag("testcontainers")
class TestcontainersIT {

	@Test
	void contextLoads() {
	}

}
