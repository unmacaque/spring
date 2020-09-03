package com.gmail.unmacaque.spring.web;

import com.gmail.unmacaque.spring.domain.Group;
import com.gmail.unmacaque.spring.domain.GroupRepository;
import com.gmail.unmacaque.spring.domain.Person;
import com.gmail.unmacaque.spring.domain.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GroupController {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private PersonRepository personRepository;

	@GetMapping("/groups")
	public Iterable<Group> getGroups(@RequestParam("member") @Nullable String uid) {
		return Optional
				.ofNullable(uid)
				.flatMap(personRepository::findByUid)
				.map(Person::getDn)
				.map(groupRepository::findByMember)
				.orElseGet(groupRepository::findAll);
	}

}
