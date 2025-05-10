package com.gmail.unmacaque.spring.ai.rag.web;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.advisor.RetrievalAugmentationAdvisor;
import org.springframework.ai.rag.retrieval.search.VectorStoreDocumentRetriever;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AiController {

	private final ChatClient chatClient;

	private final VectorStore vectorStore;

	public AiController(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
		this.chatClient = chatClientBuilder.build();
		this.vectorStore = vectorStore;
	}

	@GetMapping
	public String retrieveAndGenerate(@RequestParam(value = "message", defaultValue = "Software Developer") String message) {
		final var retriever = VectorStoreDocumentRetriever.builder()
				.vectorStore(vectorStore)
				.topK(3)
				.build();

		final var advisor = RetrievalAugmentationAdvisor.builder()
				.documentRetriever(retriever)
				.queryTransformers(query -> new Query(message))
				.build();

		return chatClient.prompt()
				.advisors(advisor)
				.user("Generate a ChatGPT prompt for the following profession: " + message)
				.call()
				.content();
	}
}
