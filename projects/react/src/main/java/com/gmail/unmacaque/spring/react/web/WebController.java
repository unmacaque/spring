package com.gmail.unmacaque.spring.react.web;

import com.gmail.unmacaque.spring.react.domain.AccountInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class WebController {

	public static final Random random = new Random();

	@GetMapping("/")
	public AccountInfo accountInfo() {
		final var currency = Currency.getInstance(Locale.GERMANY);
		final var amount = new AccountInfo.MonetaryAmount(createRandomAmount(100, 1000), currency);
		final var transfers = Stream.generate(() -> createRandomAmount(-100, 100)).limit(3)
				.map(d -> new AccountInfo.MonetaryAmount(d, currency))
				.map(m -> new AccountInfo.Transaction(m, "Jane Doe")).toList();

		return new AccountInfo("John Doe", amount, transfers);
	}

	private static BigDecimal createRandomAmount(double lower, double bound) {
		return BigDecimal.valueOf(random.nextDouble(lower, bound)).setScale(2, RoundingMode.HALF_EVEN);
	}
}
