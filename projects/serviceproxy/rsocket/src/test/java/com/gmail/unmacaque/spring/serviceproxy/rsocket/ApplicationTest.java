package com.gmail.unmacaque.spring.serviceproxy.rsocket;

import com.gmail.unmacaque.spring.serviceproxy.rsocket.domain.Forecast;
import com.gmail.unmacaque.spring.serviceproxy.rsocket.domain.ForecastService;
import com.gmail.unmacaque.spring.serviceproxy.rsocket.domain.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.time.Instant;

@SpringBootTest
class ApplicationTest {

	private static final Location LOCATION = new Location("Copenhagen", 55.68, 12.56);
	private static final Forecast FORECAST = new Forecast(LOCATION, Instant.now(), new Forecast.Temperature(8.5, Forecast.TemperatureUnit.CELSIUS));

	@Autowired
	private ForecastService service;

	@Test
	void getCurrentForecast() {
		StepVerifier
				.create(service.getCurrentForecast(LOCATION))
				.expectNextMatches(forecast -> Math.abs(forecast.temperature().value() - 8.5) < Double.MIN_NORMAL)
				.verifyComplete();
	}

	@Test
	void getForecast() {
		StepVerifier
				.create(service.getForecast(LOCATION))
				.expectNextCount(1L)
				.thenCancel()
				.verify();
	}

	@Test
	void postForecast() {
		StepVerifier
				.create(service.postForecast(FORECAST))
				.expectNext(FORECAST)
				.verifyComplete();
	}

	@TestConfiguration
	static class ServiceProxyRSocketTestConfiguration {
		@Bean
		ServiceProxyRSocketTestServer serviceProxyRSocketTestServer() {
			return new ServiceProxyRSocketTestServer();
		}
	}

	@Controller
	static class ServiceProxyRSocketTestServer {

		@MessageMapping("forecast-single")
		public Mono<Forecast> forecastSingle(@Payload Mono<Location> location) {
			return location
					.filter(loc -> loc.name().equals("Copenhagen"))
					.map(loc -> FORECAST);
		}

		@MessageMapping("forecast")
		public Flux<Forecast> forecast(@Payload Mono<Location> location) {
			return location
					.filter(loc -> loc.name().equals("Copenhagen"))
					.switchIfEmpty(Mono.error(() -> new IllegalArgumentException("Unexpected payload")))
					.thenMany(Flux
							.interval(Duration.ZERO, Duration.ofSeconds(1))
							.map(l -> FORECAST)
					);
		}

		@MessageMapping("forecast-submit")
		public Mono<Forecast> forecastSubmit(@Payload Mono<Forecast> forecast) {
			return forecast
					.filter(fc -> fc.location().name().equals("Copenhagen"))
					.switchIfEmpty(Mono.error(() -> new IllegalArgumentException("Unexpected payload")));
		}
	}
}
