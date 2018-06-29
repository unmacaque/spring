package com.gmail.unmacaque.spring;

import java.time.LocalDateTime;
import java.util.Collections;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.gmail.unmacaque.spring.domain.Person;
import com.gmail.unmacaque.spring.domain.PersonRepository;
import com.gmail.unmacaque.spring.domain.Product;
import com.gmail.unmacaque.spring.domain.ProductRepository;
import com.gmail.unmacaque.spring.domain.Purchase;
import com.gmail.unmacaque.spring.domain.PurchaseRepository;

@Component
public class Neo4jApplicationRunner implements ApplicationRunner {

	private final PersonRepository personRepository;

	private final ProductRepository productRepository;

	private final PurchaseRepository purchaseRepository;

	public Neo4jApplicationRunner(
			PersonRepository personRepository,
			ProductRepository productRepository,
			PurchaseRepository purchaseRepository) {
		this.personRepository = personRepository;
		this.productRepository = productRepository;
		this.purchaseRepository = purchaseRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Person person = Person.create("John Doe", 26, "M", Collections.emptyList());
		personRepository.save(person);
		personRepository.save(Person.create("Jane Doe", 25, "F", Collections.emptyList()));
		Product product = Product.create("CPU");
		productRepository.save(product);
		purchaseRepository.save(Purchase.create(LocalDateTime.now(), person, product));
	}

}
