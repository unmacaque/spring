package com.gmail.unmacaque.spring.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@NodeEntity
public class Person {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private int age;

	private String gender;

	@JsonManagedReference
	@Relationship(type = "PURCHASED")
	private Collection<Purchase> purchases;

	@JsonManagedReference
	@Relationship(type = "RATED")
	private Collection<ProductRating> ratings;

	@Relationship(type = "KNOWS", direction = Relationship.UNDIRECTED)
	private Collection<Person> acquaintances;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Collection<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(Collection<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Collection<ProductRating> getRatings() {
		return ratings;
	}

	public void setRatings(Collection<ProductRating> ratings) {
		this.ratings = ratings;
	}

	public Collection<Person> getAcquaintances() {
		return acquaintances;
	}

	public void setAcquaintances(Set<Person> acquaintances) {
		this.acquaintances = acquaintances;
	}

	public static Person create(String name, int age, String gender, List<Person> acquaintances) {
		Person person = new Person();
		person.name = name;
		person.age = age;
		person.gender = gender;
		person.acquaintances = acquaintances;
		return person;
	}
}
