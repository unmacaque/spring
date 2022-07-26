package com.gmail.unmacaque.spring.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@ConfigurationProperties("application")
@Validated
public record MyBeanProperties(
		@NotEmpty String myProperty,
		@NotNull List<String> myList,
		@DefaultValue Author author,
		@DefaultValue("128KB") @DataSizeUnit(DataUnit.BYTES) DataSize dataSize,
		@DefaultValue("1m") @DurationUnit(ChronoUnit.SECONDS) Duration duration
) {

	@Validated
	public record Author(@NotEmpty String name, @Email String mail) {}
}
