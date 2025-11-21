package com.gmail.unmacaque.spring.boot.starter.autoconfigure;

import com.gmail.unmacaque.spring.boot.starter.domain.MyBean;
import com.gmail.unmacaque.spring.boot.starter.domain.MyBeanImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@AutoConfiguration
@ConditionalOnClass(MyBean.class)
@EnableConfigurationProperties(MyBeanProperties.class)
public class MyBeanAutoConfiguration {

	@Autowired
	private MyBeanProperties properties;

	@Bean
	@ConditionalOnMissingBean
	public MyBean myBean() {
		return new MyBeanImpl(
				List.of(
						properties.myProperty(),
						properties.myList(),
						properties.author(),
						properties.dataSize(),
						properties.duration()
				)
		);
	}

}
