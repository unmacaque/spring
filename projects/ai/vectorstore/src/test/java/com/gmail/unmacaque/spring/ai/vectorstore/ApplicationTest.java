package com.gmail.unmacaque.spring.ai.vectorstore;

import com.gmail.unmacaque.spring.ai.vectorstore.web.AiController;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AiController.class)
class ApplicationTest {

	@Autowired
	private MockMvc mvc;

	@MockitoBean
	private VectorStore vectorStore;

	@Test
	void testFindSimilar() throws Exception {
		doReturn(List.of(new Document("it works!"))).when(vectorStore).similaritySearch(any(SearchRequest.class));

		mvc.perform(get("/").queryParam("query", "test"))
				.andExpectAll(
						status().isOk(),
						jsonPath("[0].text").value("it works!")
				);

		verify(vectorStore).similaritySearch(any(SearchRequest.class));
	}

	@Test
	void testAddDocuments() throws Exception {
		mvc.perform(post("/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								[{"content":"test"}]
								""")
				)
				.andExpectAll(
						status().isOk()
				);

		verify(vectorStore).add(any());
	}

	@Test
	void testDeleteDocuments() throws Exception {
		mvc.perform(delete("/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("""
								["1","2","3"]
								"""
						)
				)
				.andExpectAll(
						status().isOk()
				);

		verify(vectorStore).delete(anyList());
	}
}
