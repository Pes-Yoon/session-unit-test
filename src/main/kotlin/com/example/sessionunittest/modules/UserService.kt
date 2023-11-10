package com.example.sessionunittest.modules

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(
    private val repository: UserRepository,
    private val mailSender: JavaMailSender,
    private val userFactory: UserFactory,
    private val uuidHolder: UUIDHolder,
) {

    fun create(name: String, email: String): User {
        return repository.save(
            userFactory.create(name, email)
        )
    }

    fun login(userId: Int, now: LocalDateTime = LocalDateTime.now()) {
        repository.findById(userId)
            .orElseThrow()
            .copy(lastLoginAt = now)
            .let { repository.save(it) }
    }

    fun sendConfirmationEmail(userId: Int) {
        repository.findById(userId)
            .orElseThrow()
            .copy(confirmationCode = uuidHolder.newUUID())
            .let { repository.save(it) }
            .let { sendSimpleMesssage(it.email, "user confirmation", "http://something.com?code=${it.confirmationCode}") }
    }

    private fun sendSimpleMesssage(
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
