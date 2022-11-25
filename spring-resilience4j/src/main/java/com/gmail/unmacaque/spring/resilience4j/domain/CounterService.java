package com.gmail.unmacaque.spring.resilience4j.domain;

import com.gmail.unmacaque.spring.resilience4j.util.FailureException;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CounterService {

	private static final AtomicInteger atomicInteger = new AtomicInteger();

	public Integer retrieveInteger() {
		return atomicInteger.incrementAndGet();
	}

	public Integer retrieveIntegerOrThrow() throws FailureException {
		final int returnValue = atomicInteger.incrementAndGet();
		if (Integer.toString(returnValue).contains("2")) {
			throw new FailureException(returnValue + " contains the digit 2");
		}
		return returnValue;
	}
}
