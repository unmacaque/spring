package com.gmail.unmacaque.spring.autoconfigure;

import com.gmail.unmacaque.spring.domain.MyBean;
import com.gmail.unmacaque.spring.domain.MyBeanImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(MyBean.class)
@EnableConfigurationProperties(MyBeanProperties.class)
public class MyBeanAutoConfiguration {

	@Autowired
	private MyBeanProperties properties;

	@Bean
	@ConditionalOnMissingBean
	public MyBean myBean() {
		return new MyBeanImpl(
				properties.myProperty(),
				properties.myList(),
				properties.author().name(),
				properties.author().mail()
		);
	}

}
