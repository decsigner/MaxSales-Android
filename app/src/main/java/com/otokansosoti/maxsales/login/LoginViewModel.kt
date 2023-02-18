package com.otokansosoti.maxsales.login

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.otokansosoti.maxsales.common.AuthUser
import com.otokansosoti.maxsales.network.Endpoint
import com.otokansosoti.maxsales.network.ErrorModel
import com.otokansosoti.maxsales.network.Network
import com.otokansosoti.maxsales.roomdb.AutoLoginDataBase
import com.otokansosoti.maxsales.roomdb.AutoLoginTable
import com.otokansosoti.maxsales.R
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val toastMessageError = MutableLiveData<String>()
    private val showHomeActivity = MutableLiveData<Boolean>()

    fun showToastError() = toastMessageError as LiveData<String>
    fun showHomeActivity() = showHomeActivity as LiveData<Boolean>

    fun autoLogin(context: Context) {
        val autoLoginDao = AutoLoginDataBase.getInstance(context).autoLoginDao()
        val users = autoLoginDao.getAllUsers().value
        users?.let {
            login(context, it.cpf, it.password)
        }
    }

    fun login(context: Context, cpf: String, password: String) {
        val baseUrl = context.applicationContext.resources.getString(R.string.base_url)
        val server = Network.getRetrofitInstance(baseUrl)
        val endpoint = server.create(Endpoint::class.java)
        val model = PostLoginModel(cpf, password)
        val callback = endpoint.doLogin(model)

        callback.enqueue(object : Callback<LoginModel> {
            override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                if (response.code() != 200) {
                    response.errorBody()?.let {
                        val errorJson = it.string()
                        val errorObjc = Gson().fromJson(errorJson, ErrorModel::class.java)
                        updateToastError(errorObjc.error)
                    }
                }

                if (response.code() == 200) {
                    response.body()?.let {
                        AuthUser.sharedInstance.set(it.token)
                        saveAutoLogin(context, cpf, password)
                        showHomeActivity.value = true
                    }
                }
            }

            override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                updateToastError(t.message)
            }
        })
    }

    fun updateToastError(message: String?) {
        if(message.isNullOrEmpty()){
            toastMessageError.value = "Ocorreu um erro, tente novamente mais tarde"
        } else {
            toastMessageError.value = message.toString()
        }
    }

    private fun saveAutoLogin(context: Context, cpf: String, password: String) {
        val autoLoginDao = AutoLoginDataBase.getInstance(context).autoLoginDao()
        autoLoginDao.insert(AutoLoginTable(cpf, password))
    }
}