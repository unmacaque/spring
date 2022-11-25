package com.gmail.unmacaque.spring.serviceproxy.rsocket.domain;

import java.time.Instant;
import java.util.Objects;

public record Forecast(Location location, Instant instant, Temperature temperature) {
	public Forecast {
		Objects.requireNonNull(location);
		Objects.requireNonNull(instant);
		Objects.requireNonNull(temperature);
	}

	public record Temperature(double value, TemperatureUnit unit) {
		public Temperature {
			Objects.requireNonNull(unit);
		}
	}

	public enum TemperatureUnit {
		CELSIUS,
		FAHRENHEIT,
		KELVIN
	}
}
