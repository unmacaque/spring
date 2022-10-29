package com.gmail.unmacaque.spring.config;

import com.gmail.unmacaque.spring.web.WebController;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.NonNull;

public class NativeHints implements RuntimeHintsRegistrar {

	@Override
	public void registerHints(@NonNull RuntimeHints hints, ClassLoader classLoader) {
		hints.reflection().registerType(WebController.Country.class, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS);

		hints.resources().registerResource(new ClassPathResource("iso-3166-1.json"));
	}

}
