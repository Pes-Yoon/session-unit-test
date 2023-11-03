package com.example.sessionunittest.modules

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class UserFactoryTest {

    private val userFactory = UserFactoryImpl { name -> name == "null" }

    @Test
    fun `만약에 블랙리스트에 올라간 이름이라면 에러를 뱉는다`() {
        shouldThrow<Exception> { userFactory.create("null", "null@gmail.com") }
    }

    @Test
    fun `정상적인 값이 들어가면 잘 만들어진다`() {
        val user = userFactory.create("pes", "pes@gmail.com")

        user.name shouldBe "pes"
        user.email shouldBe "pes@gmail.com"
    }

}