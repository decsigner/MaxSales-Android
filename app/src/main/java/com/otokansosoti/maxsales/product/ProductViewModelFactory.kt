package com.otokansosoti.maxsales.product

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductViewModelFactory constructor(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(ProductViewModel::class.java) ->
                ProductViewModel(application)
            else ->
                throw  IllegalArgumentException("Classe desconhecida")
        }
    } as T
}