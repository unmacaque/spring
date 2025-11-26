package com.gmail.unmacaque.spring.boot.actuator.actuate.health;

import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class MyHealthHealthIndicator implements HealthIndicator {

	private static final String MESSAGE_KEY = "message";

	private final AtomicBoolean flip = new AtomicBoolean(true);

	@Override
	public Health health() {
		final var builder = new Health.Builder();
		final boolean good = flip.get();
		flip.set(!good);

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
