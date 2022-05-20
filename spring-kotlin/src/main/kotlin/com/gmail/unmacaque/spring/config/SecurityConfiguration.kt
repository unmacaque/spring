package com.gmail.unmacaque.spring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
class SecurityConfiguration {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        run {
            http {
                authorizeRequests {
                    authorize(AntPathRequestMatcher("/**", "GET"), permitAll)
                    authorize(anyRequest, authenticated)
                }
                formLogin { }
                httpBasic { }
            }
            http.build()
        }
}
