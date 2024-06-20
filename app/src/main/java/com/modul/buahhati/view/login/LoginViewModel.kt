package com.modul.buahhati.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: UserRepository,
    private val loginPreference: LoginPreference
) : ViewModel() {

    fun login(emailInput: String, passwordInput: String) =
        userRepository.login(emailInput, passwordInput)

    fun saveState(token: String, userId: String, userName: String) {
        viewModelScope.launch {
            loginPreference.svToken(token)
            loginPreference.svUserId(userId)
            loginPreference.svUserName(userName)
            loginPreference.login()
            Log.d("LoginViewModel", "Saving Token: $token, User ID: $userId, User Name: $userName")

            loginPreference.getToken().collect { savedToken ->
                Log.d("LoginViewModel", "Saved Token: $savedToken")
            }
            loginPreference.getUserId().collect { savedUserId ->
                Log.d("LoginViewModel", "Saved User ID: $savedUserId")
            }
            loginPreference.getUserName().collect { savedUserName ->
                Log.d("LoginViewModel", "Saved User Name: $savedUserName")
            }
            loginPreference.getLoginStatus().collect { isLoggedIn ->
                Log.d("LoginViewModel", "Is Logged In: $isLoggedIn")
            }
        }
    }

    fun getLoginStatus(): LiveData<Boolean?> {
        return loginPreference.getLoginStatus().asLiveData()
    }
}
