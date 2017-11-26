package com.gmail.unmacaque.spring.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.gmail.unmacaque.spring.domain.MyBean;
import com.gmail.unmacaque.spring.domain.MyBeanImpl;

@ConfigurationProperties
@ConditionalOnClass(MyBean.class)
@EnableConfigurationProperties(MyBeanProperties.class)
public class MyBeanAutoConfiguration {

	@Autowired
	private MyBeanProperties properties;

	@Bean
	@ConditionalOnMissingBean
	public MyBean myBean() {
		return new MyBeanImpl(properties.getMyProperty(), properties.getMyList());
	}

}
