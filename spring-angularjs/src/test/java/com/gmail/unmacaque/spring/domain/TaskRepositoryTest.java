package com.gmail.unmacaque.spring.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.gmail.unmacaque.spring.domain.Task;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TaskRepositoryTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetTasks() throws Exception {
		ResponseEntity<Resources<Resource<Task>>> exchange = restTemplate.exchange("/tasks", HttpMethod.GET, null,
				new ParameterizedTypeReference<Resources<Resource<Task>>>() {});

		assertThat(exchange.getBody()).hasSize(7);
		assertThat(exchange.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	public void testGetTask() throws Exception {
		ResponseEntity<Task> forEntity = restTemplate.getForEntity("/tasks/1", Task.class);

		assertThat(forEntity.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	public void testGetTask_noTasks_returns404() throws Exception {
		ResponseEntity<Task> forEntity = restTemplate.getForEntity("/tasks/123", Task.class);

		assertThat(forEntity.getStatusCode().value()).isEqualTo(404);
	}

}
