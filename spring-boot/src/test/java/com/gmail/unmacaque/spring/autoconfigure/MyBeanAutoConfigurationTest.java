package com.gmail.unmacaque.spring.autoconfigure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.support.TestPropertySourceUtils;

import com.gmail.unmacaque.spring.domain.MyBean;
import com.gmail.unmacaque.spring.domain.MyBeanImpl;

public class MyBeanAutoConfigurationTest {

	private AnnotationConfigApplicationContext context;

	@Before
	public void before() {
		context = new AnnotationConfigApplicationContext();
	}

	@After
	public void after() {
		context.close();
	}

	@Test
	public void testConfiguration() {
		TestPropertySourceUtils.addInlinedPropertiesToEnvironment(context, "application.my-property=foo", "application.my-list=foo");
		context.register(MyBeanAutoConfiguration.class);
		context.refresh();

		MyBean bean = context.getBean(MyBean.class);
		assertThat(bean).isInstanceOf(MyBeanImpl.class);
	}

}
