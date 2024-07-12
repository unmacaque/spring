package com.gmail.unmacaque.spring.micrometer.metrics.config;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Configuration(proxyBeanMethods = false)
@EnableScheduling
public class MetricsConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(MetricsConfiguration.class);

	private static final Random random = new Random();

	@Bean
	public MeterRegistryCustomizer<MeterRegistry> meterRegistryCustomizer() {
		return registry -> registry.config().commonTags(Tags.of("app", "spring-micrometer-metrics"));
	}

	@Bean
	public MeterBinder meterBinder() {
		return registry -> Gauge.builder("example.random", () -> random.nextInt(1024)).register(registry);
	}

	@Bean
	public TimedAspect timedAspect(MeterRegistry registry) {
		return new TimedAspect(registry);
	}

	@Timed(value = "example.longtasktimer", longTask = true)
	@Scheduled(fixedDelay = 1, timeUnit = TimeUnit.HOURS)
	void longTaskTimer() throws InterruptedException {
		logger.info("Starting long running task...");
		Thread.sleep(Duration.ofMinutes(30).toMillis());
		logger.info("Finished long running task");
	}
}
