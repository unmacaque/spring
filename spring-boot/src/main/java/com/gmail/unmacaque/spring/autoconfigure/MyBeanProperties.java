package com.gmail.unmacaque.spring.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@ConstructorBinding
@ConfigurationProperties("application")
@Validated
public record MyBeanProperties(
		@NotEmpty String myProperty,
		@NotNull List<String> myList,
		@DefaultValue MyBeanProperties.NestedProperties author
) {

	@ConstructorBinding
	@Validated
	public record NestedProperties(@NotEmpty String name, @Email String mail) {}
}
