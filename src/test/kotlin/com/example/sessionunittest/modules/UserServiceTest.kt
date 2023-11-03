package com.example.sessionunittest.modules

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.optional.shouldBePresent
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class UserServiceTest {

    val repository = FakeUserRepository()

    val service = UserService(
        repository = repository,
        mailSender = FakeJavaMailSender(),
        userFactory = { name, email -> if (name == "error") throw Exception() else User(name = name, email = email) }
    )

    @Test
    fun `정상적으로 create할 수 있어야 한다`() {

        val user = service.create("normal", "pes@gmail.com")

        repository.findById(user.id).shouldBePresent {
            name shouldBe "normal"
            email shouldBe "pes@gmail.com"
        }
    }

    @Test
    fun `User가 정상적으로 생성되지 않으면 예외가 발생해야 된다`() {

        shouldThrow<Exception> { service.create("error", "null@gmail.com") }

    }

}

