package com.example.sessionunittest.modules

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.optional.shouldBePresent
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UserServiceTest {

    val repository = FakeUserRepository()
    val mockSender = MockJavaMailSender()
    val fakeUuidHolder = FakeUuidHolder()

    val service = UserService(
        repository = repository,
        mailSender = mockSender,
        userFactory = { name, email -> if (name == "error") throw Exception() else User(name = name, email = email) },
        uuidHolder = fakeUuidHolder,
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

    @Test
    fun `로그인을 하면 최종 로그인 시각이 실제 로그인헀던 시각으로 변경된다`() {
        val user = service.create("normal", "pes@gmail.com")
        service.login(user.id, LocalDateTime.of(2023, 11, 10, 15, 16))

        repository.findById(user.id).shouldBePresent {
            lastLoginAt shouldBe LocalDateTime.of(2023, 11, 10, 15, 16)
        }

    }

    @Test
    fun `사용자의 이메일 주소에 랜덤한 코드가 지정된 이메일을 전송할 수 있다`() {
        val user = service.create("normal", "pes@gmail.com")
        service.sendConfirmationEmail(user.id)

        mockSender.to shouldBe user.email
        mockSender.text shouldBe "http://something.com?code=3141df9b-1496-4dbf-b9ad-a192e6364149"
    }
}

