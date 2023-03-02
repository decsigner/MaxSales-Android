package com.otokansosoti.maxsales.product

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.otokansosoti.maxsales.R
import com.otokansosoti.maxsales.common.AuthUser
import com.otokansosoti.maxsales.fragment.home.HomeModel
import com.otokansosoti.maxsales.network.Endpoint
import com.otokansosoti.maxsales.network.ErrorModel
import com.otokansosoti.maxsales.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val toastMessageError = MutableLiveData<String>()
    private val productsList = MutableLiveData<List<HomeModel>>()

    fun showToastError() = toastMessageError as LiveData<String>
    fun showProductList() = productsList as LiveData<List<HomeModel>>

    fun loadProdutsBy(category: String, context: Context) {
        val baseUrl = context.applicationContext.resources.getString(R.string.base_url)
        val server = Network.getRetrofitInstance(baseUrl)
        val endpoint = server.create(Endpoint::class.java)
        val token = "Bearer ${AuthUser.sharedInstance.getToken()}"
        val callback = endpoint.getProductsByCategory(token, category)

        callback.enqueue(object : Callback<List<HomeModel>> {
            override fun onResponse(call: Call<List<HomeModel>>, response: Response<List<HomeModel>>) {
                if (response.code() != 200) {
                    response.errorBody()?.let {
                        val errorJson = it.string()
                        val errorObjc = Gson().fromJson(errorJson, ErrorModel::class.java)
                        updateToastError(errorObjc.error)
                    }
                }

                if (response.code() == 200) {
                    response.body()?.let {
                        val dataSource: MutableList<HomeModel> = it.toMutableList()
                        productsList.value = dataSource.toList()
                    }
                }
            }

            override fun onFailure(call: Call<List<HomeModel>>, t: Throwable) {
                updateToastError(t.message)
            }
        })
    }

    private fun updateToastError(message: String?) {
        if(message.isNullOrEmpty()){
            toastMessageError.value = "Ocorreu um erro, tente novamente mais tarde"
        } else {
            toastMessageError.value = message.toString()
        }
    }
}