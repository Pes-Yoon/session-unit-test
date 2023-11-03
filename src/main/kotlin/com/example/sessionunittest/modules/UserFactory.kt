package com.example.sessionunittest.modules


fun interface UserFactory {
    fun create(name: String, email: String): User

}
