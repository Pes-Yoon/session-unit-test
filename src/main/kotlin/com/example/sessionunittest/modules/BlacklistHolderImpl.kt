package com.example.sessionunittest.modules

import org.springframework.stereotype.Component

@Component
class BlacklistHolderImpl: BlacklistHolder {

    override fun contains(name: String): Boolean {
        return name in listOf("null", "admin")
    }

}
