package com.otokansosoti.maxsales.fragment.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModelFactory constructor(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass){
        when {
            isAssignableFrom(HomeViewModel::class.java) ->
                HomeViewModel(application)
            else ->
                throw  IllegalArgumentException("Classe desconhecida")
        }
    } as T
}