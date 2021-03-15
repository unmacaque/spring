package com.gmail.unmacaque.spring.web

import com.gmail.unmacaque.spring.domain.Message
import com.gmail.unmacaque.spring.domain.MessageRepository
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.body

class MessageHandler(private val repository: MessageRepository) {

    fun getMessages(request: ServerRequest): ServerResponse {
        val messages = repository.findAll()
        return ServerResponse.ok().body(messages)
    }

    fun getMessage(request: ServerRequest): ServerResponse {
        val messageId = request.pathVariable("id").toLong()
        val message = repository.findById(messageId)
        return message
            .map { ServerResponse.ok().body(it) }
            .orElseGet(ServerResponse.notFound()::build)
    }

    fun postMessage(request: ServerRequest): ServerResponse {
        val message = request.body<Message>()
        val saved = repository.save(message)
        return ServerResponse.ok().body(saved)
    }

}
