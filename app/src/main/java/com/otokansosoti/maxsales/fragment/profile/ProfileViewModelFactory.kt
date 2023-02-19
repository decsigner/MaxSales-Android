package com.otokansosoti.maxsales.fragment.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.otokansosoti.maxsales.login.LoginViewModel

class ProfileViewModelFactory constructor(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass){
        when {
            isAssignableFrom(ProfileViewModel::class.java) ->
                ProfileViewModel(application)
            else ->
                throw  IllegalArgumentException("Classe desconhecida")
        }
    } as T
}