package com.gmail.unmacaque.spring;

import com.gmail.unmacaque.spring.domain.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.neo4j.harness.Neo4j;
import org.neo4j.harness.Neo4jBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataNeo4jTest
class Neo4jTest {

	private static Neo4j embeddedDatabaseServer;

	@BeforeAll
	static void initializeNeo4j() {
		embeddedDatabaseServer = Neo4jBuilders.newInProcessBuilder()
				.withDisabledServer()
				.build();
	}

	@AfterAll
	static void stopNeo4j() {
		embeddedDatabaseServer.close();
	}

	@DynamicPropertySource
	static void neo4jProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.neo4j.uri", embeddedDatabaseServer::boltURI);
		registry.add("spring.neo4j.authentication.username", () -> "neo4j");
		registry.add("spring.neo4j.authentication.password", () -> null);
	}

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private ProductRepository productRepository;

	@Test
	void testPersonRepository() {
		assertThat(personRepository.findAll()).isEmpty();

		personRepository.save(Person.create("John Doe", 42, Gender.MALE));

		assertThat(personRepository.findAll()).hasSize(1);
	}

	@Test
	void testPersonRepositoryFindByPurchasedTitle() {
		assertThat(personRepository.findByPurchasedTitle("USB flash drives")).isEmpty();

		final Product product = Product.create("USB flash drives");
		final Purchase purchase = Purchase.create(LocalDateTime.MIN, product);
		final Person person = Person.create("John Doe", 42, Gender.MALE).withPurchases(purchase);
		productRepository.save(product);
		personRepository.save(person);

		assertThat(personRepository.findByPurchasedTitle("USB flash drives")).hasSize(1);
	}

	@Test
	void testProductRepository() {
		assertThat(productRepository.findAll()).isEmpty();

		productRepository.save(Product.create("USB flash drives"));

		assertThat(productRepository.findAll()).hasSize(1);
	}
}
