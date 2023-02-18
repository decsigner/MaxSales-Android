package com.otokansosoti.maxsales.login

import com.google.gson.annotations.SerializedName

data class LoginModel (
    var id : String,
    var cpf : String,
    var token : String
)

data class PostLoginModel (
    var cpf : String,
    var password : String
)