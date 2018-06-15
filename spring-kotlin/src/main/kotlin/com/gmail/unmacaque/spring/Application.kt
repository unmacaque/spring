package com.gmail.unmacaque.spring

import com.gmail.unmacaque.spring.domain.Message
import com.gmail.unmacaque.spring.domain.MessageRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.stream.StreamSupport

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
    runApplication<Application>(*args)
}
