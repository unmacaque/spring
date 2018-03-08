package com.gmail.unmacaque.spring.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import com.gmail.unmacaque.spring.domain.MyBeanImpl;

public class MyBeanAutoConfigurationTest {

	private ApplicationContextRunner runner = new ApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(MyBeanAutoConfiguration.class));

	@Test
	public void testConfiguration() {
		runner.withPropertyValues("application.my-property=foo", "application.my-list=foo")
				.run(context -> {
					assertThat(context).hasSingleBean(MyBeanImpl.class);
				});
	}

	@Test
	public void testConfigurationWithoutPropertiesFailsStartup() {
		runner.run(context -> {
			assertThat(context).hasFailed();
		});
	}

}
