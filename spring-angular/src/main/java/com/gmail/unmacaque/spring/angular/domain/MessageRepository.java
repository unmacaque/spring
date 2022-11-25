package com.gmail.unmacaque.spring.angular.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {}
