package com.gmail.unmacaque.spring.config

import com.gmail.unmacaque.spring.domain.MessageRepository
import com.gmail.unmacaque.spring.web.HelloHandler
import com.gmail.unmacaque.spring.web.MessageHandler
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.function.router

@Configuration
@EnableConfigurationProperties(ApplicationProperties::class)
class RouteConfiguration {

    @Bean
    fun helloHandler(properties: ApplicationProperties) = HelloHandler(properties.text)

    @Bean
    fun routeHandler(repository: MessageRepository) = MessageHandler(repository)

    @Bean
    fun routerFunction(helloHandler: HelloHandler, messageHandler: MessageHandler) =
        router {
            "/hello".nest {
                GET("/", helloHandler::sayHello)
            }
            "/messages".nest {
                GET("/", messageHandler::getMessages)
                GET("/{id}", messageHandler::getMessage)
                POST("/", messageHandler::postMessage)
            }
        }

}
