package com.gmail.unmacaque.spring.kotlin.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Message(
    val sender: String,
    val recipient: String,
    val content: String,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = -1
)
