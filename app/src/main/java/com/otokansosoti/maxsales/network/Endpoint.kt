package com.otokansosoti.maxsales.network

import com.otokansosoti.maxsales.editprofile.UpdateProfileModel
import com.otokansosoti.maxsales.fragment.profile.ProfileModel
import com.otokansosoti.maxsales.fragment.home.HomeModel
import com.otokansosoti.maxsales.login.LoginModel
import com.otokansosoti.maxsales.login.PostLoginModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Endpoint {
    @Headers("Content-Type: application/json")
    @POST("session")
    fun doLogin(@Body userData: PostLoginModel) : Call<LoginModel>

    @GET("users")
    fun getProfile(@Header("Authorization") token: String) : Call<ProfileModel>

    @GET("users/purchases")
    fun getUserPurchases(@Header("Authorization") token: String): Call<List<HomeModel>>

    @PUT("users")
    fun updateProfile(@Header("Authorization") token: String, @Body userData: UpdateProfileModel) : Call<ProfileModel>

    @GET("home")
    fun getHomeList(@Header("Authorization") token: String) : Call<List<HomeModel>>

    @GET("files/{imageName}")
    fun getImage(@Path("imageName") imageName: String) : Call<ResponseBody>

    @GET("products/{categoryId}")
    fun getProductsByCategory(@Header("Authorization") token: String, @Path("categoryId") categoryId: String) : Call<List<HomeModel>>
}