package com.gmail.unmacaque.spring.config

import com.gmail.unmacaque.spring.domain.MessageRepository
import com.gmail.unmacaque.spring.web.RouteHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.function.router

@Configuration
class RouteConfiguration {

    @Bean
    fun routeHandler(repository: MessageRepository) = RouteHandler(repository)

    @Bean
    fun routerFunction(routeHandler: RouteHandler) =
        router {
            "/messages".nest {
                GET("/", routeHandler::getMessages)
                GET("/{id}", routeHandler::getMessage)
                POST("/", routeHandler::postMessage)
            }
        }

}
