package com.gmail.unmacaque.spring.ai.vectorstore.web;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AiController {

	private final VectorStore vectorStore;

	public AiController(VectorStore vectorStore) {
		this.vectorStore = vectorStore;
	}

	@GetMapping
	public List<Document> findSimilar(@RequestParam(value = "query") String query) {
		return vectorStore.similaritySearch(SearchRequest.builder()
				.query(query)
				.build()
		);
	}

	@PostMapping
	public void addDocuments(@RequestBody List<Document> documents) {
		vectorStore.add(documents);
	}

	@DeleteMapping
	public void deleteDocuments(@RequestBody List<String> idList) {
		vectorStore.delete(idList);
	}
}
