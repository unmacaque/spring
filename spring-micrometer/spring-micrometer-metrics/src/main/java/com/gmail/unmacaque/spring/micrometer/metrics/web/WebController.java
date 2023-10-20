package com.gmail.unmacaque.spring.micrometer.metrics.web;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class WebController {

	private static final Random random = new Random();

	private static final AtomicInteger atomicInteger = new AtomicInteger(0);

	private final MeterRegistry registry;

	public WebController(MeterRegistry registry) {
		this.registry = registry;
	}

	@GetMapping({"/", "/counter"})
	public double counter() {
		final var counter = Counter.builder("example.counter").register(registry);
		counter.increment();
		return counter.count();
	}

	@GetMapping("/gauge")
	public int gauge() {
		registry.gauge("example.gauge", atomicInteger);
		int number = random.nextInt(10);
		number = random.nextBoolean() ? -number : number;
		atomicInteger.set(number);
		return number;
	}

	@GetMapping("/timer")
	public Integer timer() throws Exception {
		final var timer = Timer.builder("example.timer").register(registry);
		final Callable<Integer> runnable = this::timed;
		return timer.recordCallable(runnable);
	}

	@Timed("example.timed")
	@GetMapping("/timed")
	public Integer timed() throws InterruptedException {
		final int sleep = random.nextInt(100) + 1;
		Thread.sleep(sleep);
		return sleep;
	}

	@GetMapping("/summary")
	public DistributionSummaryValues summary() {
		final var summary = DistributionSummary
				.builder("example.summary")
				.publishPercentiles(0.05, 0.1, 0.25, 0.5, 0.75, 0.9, 0.95)
				.minimumExpectedValue(2.0)
				.maximumExpectedValue(12.0)
				.register(registry);
		for (int i = 0; i < 10; i++) {
			final int sum = random.nextInt(6) + 1 + random.nextInt(6) + 1;
			summary.record(sum);
		}
		return new DistributionSummaryValues(summary.count(), summary.mean(), summary.max(), summary.totalAmount());
	}

	public record DistributionSummaryValues(long count, double mean, double max, double totalAmount) {}
}
