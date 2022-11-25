package com.gmail.unmacaque.spring.kotlin

import com.gmail.unmacaque.spring.kotlin.domain.Message
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@Transactional
class ApplicationTest(
    @Autowired private val mvc: MockMvc,
    @Autowired private val json: JacksonTester<Message>
) {

    @Test
    fun testGetHello() {
        mvc.get("/hello/")
            .andExpect {
                status { isOk() }
                content {
                    contentTypeCompatibleWith(MediaType.TEXT_PLAIN)
                    string("Hello World")
                }
            }
    }

    @Test
    fun testGetMessages() {
        mvc.get("/messages/")
            .andExpect {
                status { isOk() }
                content {
                    contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                    jsonPath("$.length()") { value(3) }
                }
            }
    }

    @Test
    fun testGetMessage() {
        mvc.get("/messages/1")
            .andExpect {
                status { isOk() }
                content {
                    contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                    jsonPath("$.sender") { value("Homer") }
                    jsonPath("$.recipient") { value("Marge") }
                    jsonPath("$.content") { value("I like beer.") }
                }
            }
    }

    @Test
    @WithMockUser
    fun testPostMessage() {
        mvc.post("/messages/") {
            content = json.write(Message("Homer", "Moe", "Hello Moe!")).json
            contentType = MediaType.APPLICATION_JSON
            with(SecurityMockMvcRequestPostProcessors.csrf())
        }
            .andExpect {
                status { isOk() }
                content {
                    contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                    jsonPath("$.id") { value(4) }
                    jsonPath("$.sender") { value("Homer") }
                    jsonPath("$.recipient") { value("Moe") }
                    jsonPath("$.content") { value("Hello Moe!") }
                }
            }
    }

}
