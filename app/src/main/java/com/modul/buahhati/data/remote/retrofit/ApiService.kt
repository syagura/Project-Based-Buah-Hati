package com.modul.buahhati.data.remote.retrofit

import com.modul.buahhati.data.remote.response.ChildRegisterResponse
import com.modul.buahhati.data.remote.response.ErrorResponse
import com.modul.buahhati.data.remote.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): ErrorResponse

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("child/register")
    suspend fun childRegister(
        @Field("user_id") user_id: String,
        @Field("name") name:String,
        @Field("birthdate") birthdate:String,
        @Field("gender") gender:String,
        @Field("blood_type") blood_type:String,
        @Field("body_weight") body_weight:Int,
        @Field("body_height") body_height:Int,
        @Field("head_circumference") head_circumference:Int
    ):ChildRegisterResponse

}