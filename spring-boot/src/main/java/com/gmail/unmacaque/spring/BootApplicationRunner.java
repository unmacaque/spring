package com.gmail.unmacaque.spring;

import com.gmail.unmacaque.spring.domain.MyBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class BootApplicationRunner implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(BootApplicationRunner.class);

	private final MyBean myBean;

	public BootApplicationRunner(MyBean myBean) {
		this.myBean = myBean;
	}

	@Override
	public void run(ApplicationArguments args) {
		logger.info(myBean.getString());
		myBean.getList().forEach(logger::info);

		args.getNonOptionArgs().forEach(logger::info);
		args.getOptionNames().forEach(option -> logger.info("{}={}", option, args.getOptionValues(option)));
	}

}
