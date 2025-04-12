package com.gmail.unmacaque.spring.ai.chat;

import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

	@Autowired
	private MockMvc mvc;

	@MockitoBean(answers = Answers.RETURNS_DEEP_STUBS)
	private ChatClient chatClient;

	@Test
	void testGenerate() throws Exception {
		when(chatClient.prompt().user(anyString()).call().content()).thenReturn("Test");

		mvc.perform(get("/"))
				.andExpectAll(
						status().isOk(),
						content().string("Test")
				);
	}
}
