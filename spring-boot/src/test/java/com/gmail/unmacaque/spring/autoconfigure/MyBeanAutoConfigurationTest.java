package com.gmail.unmacaque.spring.autoconfigure;

import com.gmail.unmacaque.spring.domain.MyBeanImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class MyBeanAutoConfigurationTest {

	private final ApplicationContextRunner runner = new ApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(MyBeanAutoConfiguration.class));

	@Test
	void testConfiguration() {
		runner.withPropertyValues("application.my-property=foo", "application.my-list=foo")
				.run(context -> assertThat(context).hasSingleBean(MyBeanImpl.class));
	}

	@Test
	void testConfigurationWithoutPropertiesFailsStartup() {
		runner.run(context -> assertThat(context).hasFailed());
	}

}
