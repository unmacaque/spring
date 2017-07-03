package com.gmail.unmacaque.spring;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.gmail.unmacaque.spring.config.BootApplicationProperties;

@Component
public class BootApplicationRunner implements ApplicationRunner {

	private final BootApplicationProperties applicationProperties;

	public BootApplicationRunner(BootApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(applicationProperties.getMyProperty());
		applicationProperties.getMyList().forEach(System.out::println);

		args.getNonOptionArgs().forEach(System.out::println);
		args.getOptionNames().forEach((option) -> System.out.println(option + "=" + args.getOptionValues(option)));
	}

}
