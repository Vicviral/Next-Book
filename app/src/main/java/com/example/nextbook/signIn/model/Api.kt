package com.example.nextbook.signIn.model

import com.example.nextbook.signIn.model.data.LoginResponse
import com.example.nextbook.signIn.model.data.RegisterationResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("nextbook")
    fun registerUser(
        @Field("register") register: String,
        @Field("handler") handler: String,
        @Field("pwD") pwD: String,
        @Field("fullname") fullname: String
    ):Call<RegisterationResponse>

    @FormUrlEncoded
    @POST("nextbook")
    fun userLogin(
        @Field("login") login: String,
        @Field("handler") handler: String,
        @Field("pwD") pwD: String,
    ):Call<LoginResponse>
}