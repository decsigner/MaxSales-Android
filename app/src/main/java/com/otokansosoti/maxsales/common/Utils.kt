package com.otokansosoti.maxsales.common

import android.content.Context
import android.content.Intent
import com.otokansosoti.maxsales.login.LoginActivity

fun Context.popToRoot() {
    val intent = Intent(this, LoginActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    startActivity(intent)
}