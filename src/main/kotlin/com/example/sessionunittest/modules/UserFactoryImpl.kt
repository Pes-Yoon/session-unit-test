package com.example.sessionunittest.modules

import org.springframework.stereotype.Component

@Component
class UserFactoryImpl(
    val blacklistHolder: BlacklistHolder
): UserFactory {

    override fun create(name: String, email: String): User {
        return User(
            name = validateUserName(name),   // if invalid, throw exception
            email = validateEmail(email),       // if invalid, throw exception
        )
    }

    private fun validateUserName(name: String): String {
        if (blacklistHolder.contains(name)) throw Exception("invalid name")
        if (name.length < 3) throw Exception("too short name")
        return name
    }

    private fun validateEmail(email: String): String {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex()
        if (emailRegex.matches(email).not()) throw Exception("invalid email")

        return email
    }
}
