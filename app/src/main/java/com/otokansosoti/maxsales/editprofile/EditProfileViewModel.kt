package com.otokansosoti.maxsales.editprofile

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.otokansosoti.maxsales.R
import com.otokansosoti.maxsales.common.AuthUser
import com.otokansosoti.maxsales.fragment.profile.ProfileModel
import com.otokansosoti.maxsales.network.Endpoint
import com.otokansosoti.maxsales.network.ErrorModel
import com.otokansosoti.maxsales.network.Network
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val toastMessage = MutableLiveData<String>()
    private val editSuccess = MutableLiveData<Boolean>()

    fun showToast() = toastMessage as LiveData<String>
    fun backToProfile() = editSuccess as LiveData<Boolean>

    fun updateProfile(context: Context, model: UpdateProfileModel) {
        val baseUrl = context.applicationContext.resources.getString(R.string.base_url)
        val server = Network.getRetrofitInstance(baseUrl)
        val endpoint = server.create(Endpoint::class.java)
        val token = "Bearer ${AuthUser.sharedInstance.getToken()}"
        val callback = endpoint.updateProfile(token, model)

        callback.enqueue(object : Callback<ProfileModel> {
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
                        toastMessage.value = "Perfil atualziado com sucesso"
                        editSuccess.value = true
                    }
                }
            }

            override fun onFailure(call: Call<ProfileModel>, t: Throwable) {
                updateToastError(t.message)
            }
        })
    }

    private fun updateToastError(message: String?) {
        if(message.isNullOrEmpty()){
            toastMessage.value = "Ocorreu um erro, tente novamente mais tarde"
        } else {
            toastMessage.value = message.toString()
        }
    }
}