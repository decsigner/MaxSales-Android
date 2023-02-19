package com.otokansosoti.maxsales.fragment.home.viewHolder.product

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.otokansosoti.maxsales.R
import com.otokansosoti.maxsales.common.AuthUser
import com.otokansosoti.maxsales.network.Endpoint
import com.otokansosoti.maxsales.network.ErrorModel
import com.otokansosoti.maxsales.network.Network
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel(application: Application, private val context: Context) : AndroidViewModel(application) {
    private var bitMapImage = MutableLiveData<Bitmap>()

    fun showBitMapIMage() = bitMapImage as LiveData<Bitmap>

    fun loadImage(imageName: String) {
        val baseUrl = context.applicationContext.resources.getString(R.string.base_url)
        val server = Network.getRetrofitInstance(baseUrl)
        val endpoint = server.create(Endpoint::class.java)
        val callback = endpoint.getImage(imageName)

        callback.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {
                    response.body()?.let {
                        val bytes = it.bytes()
                        bitMapImage.value = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}