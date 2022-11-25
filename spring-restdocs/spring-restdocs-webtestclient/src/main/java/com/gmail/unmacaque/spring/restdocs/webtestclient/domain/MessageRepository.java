package com.gmail.unmacaque.spring.restdocs.webtestclient.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {}
