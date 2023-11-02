package com.example.sessionunittest.modules

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class UserService(
    private val repository: UserRepository,
    private val mailSender: JavaMailSender,
) {

    fun create(name: String, email: String) {
        repository.save(
            User(
                name = validateUserName(name),   // if invalid, throw exception
                email = validateEmail(email),       // if invalid, throw exception
            )
        )
    }

    fun login(userId: Int) {
        repository.findById(userId)
            .orElseThrow()
            .copy(lastLoginAt = LocalDateTime.now())
            .let { repository.save(it) }
    }

    private fun fetchBlacklist(): List<String> {
        return listOf("null", "18x")
    }

    fun validateUserName(name: String): String {
        if (name in fetchBlacklist()) throw Exception("invalid name")
        if (name.length < 3) throw Exception("too short name")
        return name
    }

    fun validateEmail(email: String): String {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex()
        if (emailRegex.matches(email).not()) throw Exception("invalid email")

        return email
    }

    fun sendConfirmationEmail(userId: Int) {
        repository.findById(userId)
            .orElseThrow()
            .copy(confirmationCode = UUID.randomUUID().toString())
            .let { repository.save(it) }
            .let { sendSimpleMesssage(it.email, "user confirmation", "http://something.com?code=${it.confirmationCode}") }
    }

    fun sendSimpleMesssage(
        to: String,
        subject: String,
        text: String
    ) {
        val message = SimpleMailMessage()
        message.setFrom("master@something.com")
        message.setTo(to)
        message.setSubject(subject)
        message.setText(text)
        mailSender.send(message)
    }

}
