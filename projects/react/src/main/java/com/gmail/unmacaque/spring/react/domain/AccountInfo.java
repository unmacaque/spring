package com.gmail.unmacaque.spring.react.domain;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

public record AccountInfo(String fullName, MonetaryAmount currentBalance, List<Transaction> transactions) {

	public record MonetaryAmount(BigDecimal amount, Currency currency) {}

	public record Transaction(MonetaryAmount amount, String receiverFullName) {}
}
