package com.example.sessionunittest.modules

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "the_users")
data class User(

    @Id
    val id: Int = 0,

    val name: String,

    val email: String,

    val confirmationCode: String? = null,

    val lastLoginAt: LocalDateTime? = null,

    )