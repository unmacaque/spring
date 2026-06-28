package com.gmail.unmacaque.spring.boot.docker.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.logging.structured.StructuredLogFormatter;

public class LogfmtLogFormatter implements StructuredLogFormatter<ILoggingEvent> {

	@Override
	@NonNull
	public String format(@NonNull ILoggingEvent event) {
		return "time=" + event.getInstant() + " " +
				"level=" + event.getLevel() + " " +
				"thread=" + event.getThreadName() + " " +
				"logger=" + event.getLoggerName() + " " +
				"message=\"" + escape(event.getFormattedMessage()) + "\"" +
				"\n";
	}

	private static String escape(String input) {
		return input.replace("\"", "\\\"");
	}
}
