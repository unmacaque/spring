package com.gmail.unmacaque.spring.domain;

import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface QuoteRepository extends ReactiveCrudRepository<Quote, String> {

	@Tailable
	Flux<Quote> findWithTailableCursorBy();

}
