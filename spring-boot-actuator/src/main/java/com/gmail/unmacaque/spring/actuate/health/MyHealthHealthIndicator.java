package com.gmail.unmacaque.spring.actuate.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealthHealthIndicator implements HealthIndicator {

	private static final String MESSAGE_KEY = "message";

	@Override
	public Health health() {
		var builder = new Health.Builder();
		boolean good = true;

		if (good) {
			builder.up()
					.withDetail(MESSAGE_KEY, "Everything is A-OK!");
		} else {
			builder.down()
					.withDetail(MESSAGE_KEY, "Oh noes! Something has gone wrong.");
		}

		return builder.build();
	}

}
