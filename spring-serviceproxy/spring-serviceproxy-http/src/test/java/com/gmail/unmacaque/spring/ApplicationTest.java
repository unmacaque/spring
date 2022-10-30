package com.gmail.unmacaque.spring;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.gmail.unmacaque.spring.domain.Reservation;
import com.gmail.unmacaque.spring.domain.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@WireMockTest(httpPort = 8888)
@SpringBootTest
class ApplicationTest {

	@Autowired
	private ReservationService service;

	@Test
	void testGetReservation() {
		stubFor(get(urlEqualTo("/reservations/1"))
				.willReturn(
						ok()
								.withBodyFile("reservation.json")
								.withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				));

		StepVerifier.create(service.getReservation(1L))
				.expectNextMatches(registration -> registration.id() == 1L && registration.name().equals("John Doe"))
				.verifyComplete();

		verify(getRequestedFor(urlEqualTo("/reservations/1")));
	}

	@Test
	void testPostReservation() {
		stubFor(post(urlEqualTo("/reservations"))
				.withHeader(HttpHeaders.CONTENT_TYPE, equalTo(MediaType.APPLICATION_JSON_VALUE))
				.willReturn(noContent()));

		StepVerifier.create(service.postReservation(Mono.just(new Reservation(1L, "John Doe"))))
				.verifyComplete();

		verify(postRequestedFor(urlEqualTo("/reservations")));
	}

	@Test
	void testPutReservation() {
		stubFor(put(urlEqualTo("/reservations/1"))
				.withHeader(HttpHeaders.CONTENT_TYPE, equalTo(MediaType.APPLICATION_JSON_VALUE))
				.willReturn(noContent()));

		StepVerifier.create(service.putReservation(1L, Mono.just(new Reservation(1L, "Jane Doe"))))
				.verifyComplete();

		verify(putRequestedFor(urlEqualTo("/reservations/1")));
	}

	@Test
	void testDeleteReservation() {
		stubFor(delete(urlEqualTo("/reservations/1"))
				.willReturn(noContent()));

		StepVerifier.create(service.deleteReservation(1L))
				.verifyComplete();

		verify(deleteRequestedFor(urlEqualTo("/reservations/1")));
	}

	@Test
	void testClientError() {
		stubFor(get(urlEqualTo("/reservations/1"))
				.willReturn(badRequest()));

		StepVerifier.create(service.getReservation(1L))
				.expectError(WebClientResponseException.BadRequest.class)
				.verify();

		verify(getRequestedFor(urlEqualTo("/reservations/1")));
	}

	@Test
	void testServerError() {
		stubFor(get(urlEqualTo("/reservations/1"))
				.willReturn(serverError()));

		StepVerifier.create(service.getReservation(1L))
				.expectError(WebClientResponseException.InternalServerError.class)
				.verify();

		verify(getRequestedFor(urlEqualTo("/reservations/1")));
	}

}
