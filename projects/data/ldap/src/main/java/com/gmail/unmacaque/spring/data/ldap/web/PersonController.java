package com.gmail.unmacaque.spring.data.ldap.web;

import com.gmail.unmacaque.spring.data.ldap.domain.Person;
import com.gmail.unmacaque.spring.data.ldap.domain.PersonRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping({"/", "/persons"})
	public Iterable<Person> getPersons(@RequestParam("name") @Nullable String name) {
		return Optional.ofNullable(name).map(personRepository::findByNameLike).orElseGet(personRepository::findAll);
	}

	@GetMapping("/persons/{uid}")
	public Person getPersonByUid(@PathVariable("uid") String uid) {
		return personRepository.findByUid(uid).orElseThrow();
	}

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleNoSuchElementException() {}
}
