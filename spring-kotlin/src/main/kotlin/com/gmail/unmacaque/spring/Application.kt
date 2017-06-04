package com.gmail.unmacaque.spring

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.ApplicationArguments
import com.gmail.unmacaque.spring.domain.MessageRepository
import com.gmail.unmacaque.spring.domain.Message
import java.util.stream.Stream
import java.util.stream.StreamSupport
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

    @Bean
    fun init(messageRepository: MessageRepository) = ApplicationRunner {
        messageRepository.save(Message("Homer", "Marge", "I like beer."))
        messageRepository.save(Message("Bart", "everyone", "Eat my shorts!"))
        messageRepository.save(Message("Homer", "Bart", "Why you little…"))

        StreamSupport
                .stream(messageRepository.findAll().spliterator(), false)
                .forEach(::println)
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
