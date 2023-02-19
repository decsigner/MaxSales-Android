package com.otokansosoti.maxsales.fragment.profile

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
import com.otokansosoti.maxsales.roomdb.AutoLoginDataBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val toastMessageError = MutableLiveData<String>()
    private val profileUser = MutableLiveData<ProfileModel>()

    fun showToastError() = toastMessageError as LiveData<String>
    fun bindingProfile() = profileUser as LiveData<ProfileModel>

    fun loadProfile(context: Context) {
        val baseUrl = context.applicationContext.resources.getString(R.string.base_url)
        val server = Network.getRetrofitInstance(baseUrl)
        val endpoint = server.create(Endpoint::class.java)
        val token = "Bearer ${AuthUser.sharedInstance.getToken()}"
        val callback = endpoint.getProfile(token = token)

        callback.enqueue(object : Callback<ProfileModel>{
            override fun onResponse(call: Call<ProfileModel>, response: Response<ProfileModel>) {

                if (response.code() != 200) {
                    response.errorBody()?.let {
                        val errorJson = it.string()
                        val errorObjc = Gson().fromJson(errorJson, ErrorModel::class.java)
                        updateToastError(errorObjc.error)
                    }
                }

                if (response.code() == 200) {
                    response.body()?.let {
                        profileUser.value = it
                    }
                }
            }

            override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                updateToastError(t.message)
            }
        })
    }

    fun logoutUser(context: Context, cpf: String) {
        AuthUser.sharedInstance.set("")
        val autoLoginDao = AutoLoginDataBase.getInstance(context).autoLoginDao()
        autoLoginDao.delete(cpf)
    }

    private fun updateToastError(message: String?) {
        if(message.isNullOrEmpty()){
            toastMessageError.value = "Ocorreu um erro, tente novamente mais tarde"
        } else {
            toastMessageError.value = message.toString()
        }
    }
}