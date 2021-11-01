package com.gmail.unmacaque.spring.web;

import com.gmail.unmacaque.spring.domain.ForecastServiceGrpc;
import com.gmail.unmacaque.spring.domain.Location;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.Exceptions;
import reactor.core.publisher.Mono;

@RestController
public class ForecastController {

	private final ForecastServiceGrpc.ForecastServiceBlockingStub forecastService;

	public ForecastController(ForecastServiceGrpc.ForecastServiceBlockingStub forecastService) {
		this.forecastService = forecastService;
	}

	@GetMapping("/{name}")
	public Mono<String> getForecast(@PathVariable String name) {
		return Mono
				.fromCallable(() -> forecastService.forecast(Location.newBuilder().setName(name).build()))
				.map(ForecastController::protobufToJson);
	}

	private static String protobufToJson(MessageOrBuilder messageOrBuilder) {
		try {
			return JsonFormat.printer().print(messageOrBuilder);
		} catch (InvalidProtocolBufferException e) {
			throw Exceptions.propagate(e);
		}
	}
}
