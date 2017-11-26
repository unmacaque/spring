package com.gmail.unmacaque.spring.domain;

import java.util.List;

public class MyBeanImpl implements MyBean {

	private final String myString;

	private final List<String> myList;

	public MyBeanImpl(String myString, List<String> myList) {
		this.myString = myString;
		this.myList = myList;
	}

	@Override
	public String getString() {
		return myString;
	}

	@Override
	public List<String> getList() {
		return myList;
	}

}
