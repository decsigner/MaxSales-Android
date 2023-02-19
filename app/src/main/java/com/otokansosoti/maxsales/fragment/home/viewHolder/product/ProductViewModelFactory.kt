package com.otokansosoti.maxsales.fragment.home.viewHolder.product

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProductViewModelFactory constructor(private val application: Application, private  val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass){
        when {
            isAssignableFrom(ProductViewModel::class.java) ->
                ProductViewModel(application, context)
            else ->
                throw  IllegalArgumentException("Classe desconhecida")
        }
    } as T
}