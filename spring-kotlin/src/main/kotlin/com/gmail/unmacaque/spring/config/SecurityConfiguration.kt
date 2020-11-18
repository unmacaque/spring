package com.gmail.unmacaque.spring.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke

@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http {
            authorizeRequests {
                authorize({ it.method == "GET" }, permitAll)
                authorize(anyRequest, authenticated)
            }
            formLogin { }
            httpBasic { }
        }
    }
}
