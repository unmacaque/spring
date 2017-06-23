package com.gmail.unmacaque.spring.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Column

@Entity
data class Message(
        val sender: String,
        val to: String,
        val content: String,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1) {}
