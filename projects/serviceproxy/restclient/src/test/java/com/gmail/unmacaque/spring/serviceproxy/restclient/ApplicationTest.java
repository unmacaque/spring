package com.gmail.unmacaque.spring.serviceproxy.restclient;

import com.gmail.unmacaque.spring.serviceproxy.restclient.domain.Reservation;
import com.gmail.unmacaque.spring.serviceproxy.restclient.domain.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
@AutoConfigureMockRestServiceServer
class ApplicationTest {

	@Autowired
	private ReservationService service;

	@Autowired
	private MockRestServiceServer server;

	@Test
	void testGetReservation() {
		server.expect(once(), requestToUriTemplate("/reservations/{id}", 1))
				.andExpect(method(HttpMethod.GET))
				.andRespond(
						withSuccess()
								.body("{\"id\":1,\"name\":\"John Doe\"}")
								.contentType(MediaType.APPLICATION_JSON)
				);

		final Reservation result = service.getReservation(1L);
		server.verify();

		assertThat(result).extracting(Reservation::id, Reservation::name).containsExactly(1L, "John Doe");
	}

	@Test
	void testPostReservation() {
		server.expect(once(), requestTo("/reservations"))
				.andExpect(method(HttpMethod.POST))
				.andRespond(
						withSuccess()
								.body("{\"id\":1,\"name\":\"John Doe\"}")
								.contentType(MediaType.APPLICATION_JSON)
				);

		final Reservation result = service.postReservation(new Reservation(1L, "John Doe"));
		server.verify();

		assertThat(result).extracting(Reservation::id, Reservation::name).containsExactly(1L, "John Doe");
	}

	@Test
	void testPutReservation() {
		server.expect(once(), requestToUriTemplate("/reservations/{id}", 1))
				.andExpect(method(HttpMethod.PUT))
				.andRespond(
						withSuccess()
								.body("{\"id\":1,\"name\":\"Jane Doe\"}")
								.contentType(MediaType.APPLICATION_JSON)
				);

		final Reservation result = service.putReservation(1L, new Reservation(1L, "Jane Doe"));
		server.verify();

		assertThat(result).extracting(Reservation::id, Reservation::name).containsExactly(1L, "Jane Doe");
	}

	@Test
	void testDeleteReservation() {
		server.expect(once(), requestToUriTemplate("/reservations/{id}", 1))
				.andExpect(method(HttpMethod.DELETE))
				.andRespond(
						withSuccess()
								.body("{\"id\":1,\"name\":\"John Doe\"}")
								.contentType(MediaType.APPLICATION_JSON)
				);

		final Reservation result = service.deleteReservation(1L);
		server.verify();

		assertThat(result).extracting(Reservation::id, Reservation::name).containsExactly(1L, "John Doe");
	}
}
