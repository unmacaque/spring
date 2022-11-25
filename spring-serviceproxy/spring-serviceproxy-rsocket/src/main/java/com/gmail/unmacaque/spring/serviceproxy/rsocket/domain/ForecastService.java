package com.gmail.unmacaque.spring.serviceproxy.rsocket.domain;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.rsocket.service.RSocketExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ForecastService {

	@RSocketExchange("forecast-single")
	Mono<Forecast> getCurrentForecast(@Payload Location location);

	@RSocketExchange("forecast")
	Flux<Forecast> getForecast(@Payload Location location);

	@RSocketExchange("forecast-submit")
	Mono<Forecast> postForecast(@Payload Forecast forecast);
}
