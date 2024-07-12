package com.gmail.unmacaque.spring.grpc.server.domain;

import com.gmail.unmacaque.spring.grpc.domain.ForecastServiceGrpc;
import com.gmail.unmacaque.spring.grpc.domain.Location;
import com.gmail.unmacaque.spring.grpc.domain.WeatherForecast;
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
