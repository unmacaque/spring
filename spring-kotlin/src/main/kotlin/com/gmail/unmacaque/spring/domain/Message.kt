package com.gmail.unmacaque.spring.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Message(
    val sender: String,
    val recipient: String,
    val content: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = -1
)
