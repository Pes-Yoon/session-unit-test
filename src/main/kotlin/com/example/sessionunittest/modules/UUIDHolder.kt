package com.example.sessionunittest.modules

import org.springframework.stereotype.Component
import java.util.*

interface UUIDHolder {
    fun newUuid(): String

}

@Component
class UUidHolderImpl: UUIDHolder {

    override fun newUuid(): String {
        return UUID.randomUUID().toString()
    }

}