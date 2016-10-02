package com.gmail.unmacaque.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationHandler implements ApplicationRunner {

	@Autowired
	private ApplicationProperties applicationProperties;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(applicationProperties.getMyProperty());
		applicationProperties.getMyList().forEach(System.out::println);

		args.getNonOptionArgs().forEach(System.out::println);
		args.getOptionNames().forEach((option) -> System.out.println(option + "=" + args.getOptionValues(option)));
	}

}
