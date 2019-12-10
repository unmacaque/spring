package com.gmail.unmacaque.spring;

import com.gmail.unmacaque.spring.domain.Person;
import com.gmail.unmacaque.spring.domain.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DataNeo4jTest
class Neo4jTest {

	@Autowired
	private PersonRepository personRepository;

	@Test
	void testPersonRepository() {
		assertThat(personRepository.findAll()).isEmpty();

		personRepository.save(Person.create("John Doe", 42, "male", Collections.emptyList()));

		assertThat(personRepository.findAll()).hasSize(1);
	}
}
