package com.gmail.unmacaque.spring.ai.mcp.server;

import org.junit.jupiter.api.Test;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTest {

	@Autowired
	private ToolCallbackProvider toolCallbackProvider;

	@Test
	void testTool() {
		assertThat(toolCallbackProvider.getToolCallbacks()).hasOnlyOneElementSatisfying(toolCallback -> {
			final var toolDefinition = toolCallback.getToolDefinition();
			assertThat(toolDefinition.name()).isEqualTo("getCurrentDateTime");
		});
	}
}
