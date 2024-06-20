package com.modul.buahhati.data.remote.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.data.remote.response.AnalysisResponse
import com.modul.buahhati.data.remote.response.AnalysisResultResponse
import com.modul.buahhati.data.remote.response.ChildRegisterResponse
import com.modul.buahhati.data.remote.response.DataItem
import com.modul.buahhati.data.remote.response.ErrorResponse
import com.modul.buahhati.data.remote.response.LoginResponse
import com.modul.buahhati.data.remote.response.ResultData
import com.modul.buahhati.data.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException

class UserRepository(
    private var apiService: ApiService,
    private var loginPreference: LoginPreference
) {

    fun register(
        name: String,
        username: String,
        email: String,
        password: String
    ): LiveData<Result<ErrorResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.register(name, username, email, password)
                emit(Result.Success(response))
            } catch (e: HttpException) {
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
            try {
                val response = apiService.login(email, password)
                val tokenData = response.data
                if (tokenData != null) {
                    loginPreference.svToken(tokenData.accessToken ?: "")
                    loginPreference.svUserName(response.user ?: "")
                    emit(Result.Success(response))
                } else {
                    emit(Result.Error("There is something error"))
                }
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val error = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                emit(Result.Error(error.message.toString()))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }


    fun regisAnak(
        user_id: String,
        name: String,
        birthdate: String,
        gender: String,
        blood_type: String,
        body_weight: Int,
        body_height: Int,
        head_circumference: Int
    ): LiveData<Result<ChildRegisterResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.childRegister(
                    user_id,
                    name,
                    birthdate,
                    gender,
                    blood_type,
                    body_weight,
                    body_height,
                    head_circumference
                )
                emit(Result.Success(response))
            } catch (e: HttpException) {
                val json_inString = e.response()?.errorBody()?.string()
                val error = Gson().fromJson(json_inString, ErrorResponse::class.java)
                emit(Result.Error(error.message.toString()))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    suspend fun getChildren(userId: String): Result<List<ChildRegisterResponse>> {
        return try {
            val response = apiService.getChildren(userId)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody?.status == true) {
                    Result.Success(responseBody.data)
                } else {
                    Result.Error(responseBody?.statusCode?.toString() ?: "Unknown error")
                }
            } else {
                Result.Error(response.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error")
        }
    }

    fun getArticles(): LiveData<Result<List<DataItem?>>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val response = apiService.getArticles()
            val articles = response.data ?: emptyList()
            emit(Result.Success(articles))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun addDataPertumbuhan(
        child_id: String,
        date: String,
        age: Int,
        gender: String,
        weight: Int,
        height: Int,
        headCircumference: Int
    ): LiveData<Result<AnalysisResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.analisisPertumbuhan(
                child_id, date, age, gender, weight, height, headCircumference
            )
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val json_inString = e.response()?.errorBody()?.string()
            val error = Gson().fromJson(json_inString, ErrorResponse::class.java)
            emit(Result.Error(error.message.toString()))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getAnalysis(analysis_id: String): LiveData<Result<AnalysisResultResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAnalysis(analysis_id)
            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody?.status == true) {
                    emit(Result.Success(responseBody))
                } else {
                    emit(Result.Error(responseBody?.statusCode?.toString() ?: "Response error"))
                }
            } else {
                emit(Result.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "getAnalysis error"))
        }
    }

    fun getAllAnalysis(child_id: String): LiveData<Result<List<AnalysisResultResponse>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAllAnalysis(child_id)
            if (response.isSuccessful) {
                val responseBody = response.body()
                Log.d("UserRepository", "Response body: $responseBody") // Tambahkan log
                if (responseBody != null && responseBody.status == true && responseBody.data != null) {
                    emit(Result.Success(responseBody.data))
                } else {
                    emit(Result.Error("No data available"))
                }
            } else {
                emit(Result.Error("Response error: ${response.message()}"))
            }
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
