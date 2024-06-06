package com.modul.buahhati.data.remote.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.data.remote.response.ErrorResponse
import com.modul.buahhati.data.remote.response.LoginResponse
import com.modul.buahhati.data.remote.retrofit.ApiService
import retrofit2.HttpException

class UserRepository (
    private var apiService: ApiService,
    private var loginPreference: LoginPreference
){

    fun register(name:String, username:String, email:String, password:String):LiveData<Result<ErrorResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.register(name,username, email, password)
                emit(Result.Success(response))
            }catch (e : HttpException){
                val json_inString = e.response()?.errorBody()?.string()
                val error = Gson().fromJson(json_inString, ErrorResponse::class.java)
                emit(Result.Error(error.message.toString()))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    fun login(email: String, password: String): LiveData<Result<LoginResponse>> =
        liveData {
            emit(Result.Loading)
            try{
                val response = apiService.login(email, password)
                val user = response.data
                if (user != null){
                    emit(Result.Success(response))
                }else{
                    emit(Result.Error("There is something error"))
                }
            }catch (e: HttpException) {
                val Json_inString = e.response()?.errorBody()?.string()
                val error = Gson().fromJson(Json_inString, ErrorResponse::class.java)
                emit(Result.Error(error.message.toString()))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(apiService: ApiService, preferences: LoginPreference): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, preferences).also { instance = it }
            }
    }
}