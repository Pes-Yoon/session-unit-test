package com.example.sessionunittest.modules

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class UserController(
    private val service: UserService,
) {

    fun create(name: String, email: String): User {
        return service.create(name, email)
    }

    fun login(userId: Int, now: LocalDateTime) {
        service.login(userId)
    }

    fun sendConfirmationEmail(userId: Int) {
        service.sendConfirmationEmail(userId)
    }

}
