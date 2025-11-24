package com.gmail.unmacaque.spring.ai.rag.web;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.document.Document;
import org.springframework.ai.model.chat.client.autoconfigure.ChatClientAutoConfiguration;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AiController.class)
@ImportAutoConfiguration(ChatClientAutoConfiguration.class)
class AiControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockitoBean
	private ChatModel chatModel;

	@MockitoBean
	private VectorStore vectorStore;

	private static ChatResponse buildChatResponse(String content) {
		return ChatResponse.builder().generations(List.of(new Generation(new AssistantMessage(content)))).build();
	}

	@Test
	void testRetrieveAndGenerate() throws Exception {
		when(chatModel.call(any(Prompt.class))).thenReturn(buildChatResponse("It works!"));
		when(vectorStore.similaritySearch(any(SearchRequest.class))).thenReturn(List.of(new Document("Test")));

		mvc.perform(get("/")
						.queryParam("message", "Test")
				)
				.andExpectAll(
						status().isOk(),
						content().string("It works!")
				);

		final var captor = ArgumentCaptor.forClass(SearchRequest.class);
		verify(vectorStore, times(1)).similaritySearch(captor.capture());
		assertThat(captor.getValue().getQuery()).isEqualTo("Test");
	}
}
