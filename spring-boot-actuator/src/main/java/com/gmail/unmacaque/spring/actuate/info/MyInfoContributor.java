package com.gmail.unmacaque.spring.actuate.info;

import java.time.LocalDateTime;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class MyInfoContributor implements InfoContributor {

	@Override
	public void contribute(Builder builder) {
		builder.withDetail("myInfo", LocalDateTime.now().toString());
	}

}
