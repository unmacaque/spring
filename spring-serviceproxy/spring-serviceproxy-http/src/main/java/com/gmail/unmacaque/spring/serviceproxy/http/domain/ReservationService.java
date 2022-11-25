package com.gmail.unmacaque.spring.serviceproxy.http.domain;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;
import reactor.core.publisher.Mono;

@HttpExchange(url = "/reservations", accept = MediaType.APPLICATION_JSON_VALUE)
public interface ReservationService {

	@GetExchange("{id}")
	Mono<Reservation> getReservation(@PathVariable Long id);

	@PostExchange
	Mono<Reservation> postReservation(@RequestBody Mono<Reservation> reservation);

	@PutExchange("{id}")
	Mono<Reservation> putReservation(@PathVariable Long id, @RequestBody Mono<Reservation> reservation);

	@DeleteExchange("{id}")
	Mono<Reservation> deleteReservation(@PathVariable Long id);

}
