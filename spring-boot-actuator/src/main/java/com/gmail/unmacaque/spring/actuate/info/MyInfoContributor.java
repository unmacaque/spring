package com.gmail.unmacaque.spring.actuate.info;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyInfoContributor implements InfoContributor {

	@Override
	public void contribute(Builder builder) {
		builder.withDetail("myInfo", LocalDateTime.now().toString());
	}

}
