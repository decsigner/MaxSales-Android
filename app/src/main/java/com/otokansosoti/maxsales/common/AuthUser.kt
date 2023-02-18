package com.otokansosoti.maxsales.common

class AuthUser {
    companion object {
        val sharedInstance = AuthUser()
    }

    private var token : String = ""

    fun set(token: String) {
        this.token = token
    }

    fun getToken () : String {
        return token
    }
}