package com.gmail.unmacaque.spring.serviceproxy.restclient.domain;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

@HttpExchange(url = "/reservations", accept = MediaType.APPLICATION_JSON_VALUE)
public interface ReservationService {

	@GetExchange("{id}")
	Reservation getReservation(@PathVariable Long id);

	@PostExchange
	Reservation postReservation(@RequestBody Reservation reservation);

	@PutExchange("{id}")
	Reservation putReservation(@PathVariable Long id, @RequestBody Reservation reservation);

	@DeleteExchange("{id}")
	Reservation deleteReservation(@PathVariable Long id);

}
