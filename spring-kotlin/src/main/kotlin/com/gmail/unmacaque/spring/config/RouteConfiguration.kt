package com.gmail.unmacaque.spring.config

import com.gmail.unmacaque.spring.domain.MessageRepository
import com.gmail.unmacaque.spring.web.MessageHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.function.router

@Configuration
class RouteConfiguration {

    @Bean
    fun routeHandler(repository: MessageRepository) = MessageHandler(repository)

    @Bean
    fun routerFunction(messageHandler: MessageHandler) =
        router {
            "/messages".nest {
                GET("/", messageHandler::getMessages)
                GET("/{id}", messageHandler::getMessage)
                POST("/", messageHandler::postMessage)
            }
        }

}
