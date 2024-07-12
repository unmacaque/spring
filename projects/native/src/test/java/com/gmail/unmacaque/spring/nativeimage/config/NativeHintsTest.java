package com.gmail.unmacaque.spring.nativeimage.config;

import com.gmail.unmacaque.spring.nativeimage.web.WebController;
import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.predicate.RuntimeHintsPredicates;

import static org.assertj.core.api.Assertions.assertThat;

class NativeHintsTest {

	@Test
	void testNativeHints() {
		final var hints = new RuntimeHints();
		new NativeHints().registerHints(hints, getClass().getClassLoader());

		assertThat(RuntimeHintsPredicates.reflection().onType(WebController.Country.class)).accepts(hints);
		assertThat(RuntimeHintsPredicates.resource().forResource("iso-3166-1.json")).accepts(hints);
	}
}
