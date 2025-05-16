package com.gmail.unmacaque.spring.grpc.server.domain;

import com.gmail.unmacaque.spring.grpc.domain.ForecastServiceGrpc;
import com.gmail.unmacaque.spring.grpc.domain.Location;
import com.gmail.unmacaque.spring.grpc.domain.WeatherForecast;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ForecastService extends ForecastServiceGrpc.ForecastServiceImplBase {

	private static final Random random = new Random();

	@Override
	public void forecast(Location request, StreamObserver<WeatherForecast> responseObserver) {
		final var weather = WeatherForecast
				.newBuilder()
				.setLocation(request.toBuilder()
						.setLatitude(randomDecimal(-90, 90, 6))
						.setLongitude(randomDecimal(-180, 180, 6))
						.build()
				)
				.setCondition(WeatherForecast.Condition.forNumber(random.nextInt(7)))
				.setTemperature(randomDecimal(-20, 33, 1))
				.setWindSpeed(random.nextInt(30))
				.setHumidity(random.nextInt(20, 100))
				.build();

		responseObserver.onNext(weather);
		responseObserver.onCompleted();
	}

	private double randomDecimal(int origin, int bound, int decimals) {
		return Math.round(random.nextDouble(origin, bound) * Math.pow(10, decimals)) / Math.pow(10, decimals);
	}
}
