package com.gmail.unmacaque.spring.web;

import com.gmail.unmacaque.spring.domain.Person;
import com.gmail.unmacaque.spring.domain.PersonRepository;
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
	public Iterable<Person> getPersons(@RequestParam("name") Optional<String> name) {
		return name.map(personRepository::findByNameLike).orElseGet(personRepository::findAll);
	}

	@GetMapping("/persons/{uid}")
	public Person getPersonByUid(@PathVariable("uid") String uid) {
		return personRepository.findByUid(uid).orElseThrow();
	}

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleNoSuchElementException() {
	}
}
