package com.otokansosoti.maxsales.fragment.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.otokansosoti.maxsales.R
import com.otokansosoti.maxsales.common.AuthUser
import com.otokansosoti.maxsales.network.Endpoint
import com.otokansosoti.maxsales.network.ErrorModel
import com.otokansosoti.maxsales.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val toastMessageError = MutableLiveData<String>()
    private val homeList = MutableLiveData<List<HomeModel>>()

    fun showToastError() = toastMessageError as LiveData<String>
    fun setupHomeList() = homeList as LiveData<List<HomeModel>>

    fun loadHomeList(context: Context) {
        val baseUrl = context.applicationContext.resources.getString(R.string.base_url)
        val server = Network.getRetrofitInstance(baseUrl)
        val endpoint = server.create(Endpoint::class.java)
        val token = "Bearer ${AuthUser.sharedInstance.getToken()}"
        val callback = endpoint.getHomeList(token = token)

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
                        val helpers = dataSource.filter { it.type == "help" }
                        val logoObject = dataSource.find { it.type == "logo" }
                        dataSource.remove(logoObject)
                        dataSource.removeAll(helpers)

                        homeList.value = dataSource.toList()
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