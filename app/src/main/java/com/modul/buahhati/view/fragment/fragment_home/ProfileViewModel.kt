package com.modul.buahhati.view.fragment.fragment_home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.data.remote.repository.UserRepository
import com.modul.buahhati.data.remote.response.ChildRegisterResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.firstOrNull
import android.util.Log

class ProfileViewModel(private val preference: LoginPreference, private val userRepository: UserRepository) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            preference.logout()
        }
    }

    fun getUserId(): LiveData<String?> {
        return liveData {
            val userId = preference.getUserId().firstOrNull()
            Log.d("ProfileViewModel", "User ID: $userId")
            emit(userId)
        }
    }

    fun getUserName(): LiveData<String?> {
        return liveData {
            val userName = preference.getUserName().firstOrNull()
            emit(userName)
        }
    }

    fun getChildren(parentId: String): LiveData<Result<List<ChildRegisterResponse>>> {
        return liveData {
            emit(Result.Loading)
            try {
                val response = userRepository.getChildren(parentId)
                emit(response)
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error loading children data", e)
                emit(Result.Error(e.message ?: "Unknown error"))
            }
        }
    }
}
