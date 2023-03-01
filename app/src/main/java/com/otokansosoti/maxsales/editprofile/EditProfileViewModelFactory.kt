package com.otokansosoti.maxsales.editprofile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EditProfileViewModelFactory constructor(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass){
        when {
            isAssignableFrom(EditProfileViewModel::class.java) ->
                EditProfileViewModel(application)
            else ->
                throw  IllegalArgumentException("Classe desconhecida")
        }
    } as T
}