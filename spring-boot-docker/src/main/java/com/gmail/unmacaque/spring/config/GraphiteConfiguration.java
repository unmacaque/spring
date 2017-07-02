package com.gmail.unmacaque.spring.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.MetricsDropwizardAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;

@Configuration
@ConditionalOnClass(GraphiteReporter.class)
@AutoConfigureAfter(MetricsDropwizardAutoConfiguration.class)
public class GraphiteConfiguration {

	@Value("${spring.application.name}")
	private String applicationName;

	@Bean(destroyMethod = "close")
	public Graphite graphite() {
		return new Graphite("localhost", 2003);
	}

	@Bean
	public GraphiteReporter graphiteReporter(Graphite graphite, MetricRegistry registry) {
		GraphiteReporter reporter = GraphiteReporter.forRegistry(registry)
				.prefixedWith(applicationName)
				.convertRatesTo(TimeUnit.SECONDS)
				.convertDurationsTo(TimeUnit.MILLISECONDS)
				.filter(MetricFilter.ALL)
				.build(graphite);
		reporter.start(15, TimeUnit.SECONDS);
		return reporter;
	}

}
