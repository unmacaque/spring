package com.gmail.unmacaque.spring.domain;

import io.grpc.stub.StreamObserver;

public class ForecastService extends ForecastServiceGrpc.ForecastServiceImplBase {

	@Override
	public void forecast(Location request, StreamObserver<WeatherForecast> responseObserver) {
		final var weather = WeatherForecast
				.newBuilder()
				.setLocation(request)
				.setCondition(WeatherForecast.Condition.SUNNY)
				.setTemperature(29)
				.setWindSpeed(5)
				.setHumidity(30)
				.build();
		responseObserver.onNext(weather);
		responseObserver.onCompleted();
	}
}
