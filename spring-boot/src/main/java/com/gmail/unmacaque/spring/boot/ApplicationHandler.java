package com.gmail.unmacaque.spring.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationHandler implements ApplicationRunner {

	@Value("${application.my-property}")
	private String myProperty;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(myProperty);
		System.out.println(args.getNonOptionArgs());

		args.getOptionNames().forEach((option) -> System.out.println(option + "=" + args.getOptionValues(option)));
	}

}
