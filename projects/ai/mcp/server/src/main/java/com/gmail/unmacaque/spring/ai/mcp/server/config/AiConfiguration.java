package com.gmail.unmacaque.spring.ai.mcp.server.config;

import com.gmail.unmacaque.spring.ai.mcp.server.domain.DateTimeTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class AiConfiguration {

	@Bean
	ToolCallbackProvider toolCallbackProvider(DateTimeTools dateTimeTools) {
		return MethodToolCallbackProvider.builder().toolObjects(dateTimeTools).build();
	}
}
