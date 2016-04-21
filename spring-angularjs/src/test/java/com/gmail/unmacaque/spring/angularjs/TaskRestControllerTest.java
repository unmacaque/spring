package com.gmail.unmacaque.spring.angularjs;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
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
	private MockMvc mockMvc;

	@After
	public void cleanup() {
		reset(taskRepositoryMock);
	}

	@Test
	public void testGetTasks() throws Exception {
		when(taskRepositoryMock.getTasks()).thenReturn(Collections.singleton(new Task()));

		mockMvc.perform(get("/tasks"))
		.andExpect(status().isOk());

		verify(taskRepositoryMock).getTasks();
	}

	@Test
	public void testGetTask() throws Exception {
		when(taskRepositoryMock.getTask(123)).thenReturn(new Task(123, "aTitle", LocalDateTime.now(), "aText", false));

		mockMvc.perform(get("/tasks/123"))
		.andExpect(status().isOk());
	}

	@Test
	public void testGetTask_noTasks_returns404() throws Exception {
		mockMvc.perform(get("/tasks/123"))
		.andExpect(status().isNotFound());
	}

}
