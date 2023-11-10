package com.example.sessionunittest.modules

import org.springframework.stereotype.Component
import java.util.*

interface UUIDHolder {

    fun newUUID(): String

}

@Component
class UUIDHolderImpl: UUIDHolder {

    override fun newUUID(): String {
        return UUID.randomUUID().toString()
    }

}
