package com.gmail.unmacaque.spring.angularjs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TaskRestControllerTest {

	@Configuration
	@ComponentScan
	@EnableAutoConfiguration
	static class Config {
		@Bean
		public TaskRepository taskRepository() {
			return mock(TaskRepository.class);
		}
	}

	@Autowired
	@Qualifier("taskRepository")
	private TaskRepository taskRepositoryMock;

	@Autowired
	private TestRestTemplate restTemplate;

	@After
	public void cleanup() {
		reset(taskRepositoryMock);
	}

	@Test
	public void testGetTasks() throws Exception {
		when(taskRepositoryMock.getTasks()).thenReturn(Collections.singleton(new Task()));

		ResponseEntity<List<Task>> exchange = restTemplate.exchange("/tasks", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Task>>() {});

		assertThat(exchange.getBody()).hasSize(1);
		assertThat(exchange.getStatusCode().value()).isEqualTo(200);

		verify(taskRepositoryMock).getTasks();
	}

	@Test
	public void testGetTask() throws Exception {
		when(taskRepositoryMock.getTask(123)).thenReturn(new Task(123, "aTitle", LocalDateTime.now(), "aText", false));

		ResponseEntity<Task> forEntity = restTemplate.getForEntity("/tasks/123", Task.class);

		assertThat(forEntity.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	public void testGetTask_noTasks_returns404() throws Exception {
		ResponseEntity<Task> forEntity = restTemplate.getForEntity("/tasks/123", Task.class);

		assertThat(forEntity.getStatusCode().value()).isEqualTo(404);
	}

}
