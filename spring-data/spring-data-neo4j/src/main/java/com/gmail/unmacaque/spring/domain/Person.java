package com.gmail.unmacaque.spring.domain;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class Person {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private int age;

	private Gender gender;

	@Relationship(type = "PURCHASED", direction = Relationship.Direction.INCOMING)
	private List<Purchase> purchases;

	@Relationship(type = "RATED", direction = Relationship.Direction.INCOMING)
	private List<ProductRating> ratings;

	@Relationship(type = "KNOWS", direction = Relationship.Direction.INCOMING)
	private List<Person> acquaintances;

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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public List<ProductRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<ProductRating> ratings) {
		this.ratings = ratings;
	}

	public List<Person> getAcquaintances() {
		return acquaintances;
	}

	public void setAcquaintances(List<Person> acquaintances) {
		this.acquaintances = acquaintances;
	}

	public static Person create(String name, int age, Gender gender) {
		final Person person = new Person();
		person.name = name;
		person.age = age;
		person.gender = gender;
		return person;
	}

	public Person withAcquaintances(Person... persons) {
		final Person person = create(this.name, this.age, this.gender);
		person.setAcquaintances(List.of(persons));
		return person;
	}

	public Person withPurchases(Purchase... purchases) {
		final Person person = create(this.name, this.age, this.gender);
		person.setPurchases(List.of(purchases));
		return person;
	}
}
