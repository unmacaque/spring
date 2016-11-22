package com.gmail.unmacaque.spring.thymeleaf.format;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.format.Formatter;

public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

	@Override
	public String print(LocalDateTime object, Locale locale) {
		return object.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
	}

	@Override
	public LocalDateTime parse(String text, Locale locale) throws ParseException {
		return LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

}
