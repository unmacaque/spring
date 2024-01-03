package com.gmail.unmacaque.spring.modulith;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ApplicationModulesTest {

	ApplicationModules modules = ApplicationModules.of(Application.class);

	@Test
	void verifyApplicationModules() {
		ApplicationModules.of(Application.class).verify();
	}

	@Test
	void writeDocumentationSnippets() {
		new Documenter(modules)
				.writeModulesAsPlantUml()
				.writeIndividualModulesAsPlantUml();
	}

}
