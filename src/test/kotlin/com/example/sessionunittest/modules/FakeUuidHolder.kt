package com.example.sessionunittest.modules

class FakeUuidHolder: UUIDHolder {
    override fun newUuid(): String {
        return "3141df9b-1496-4dbf-b9ad-a192e6364149"
    }

}