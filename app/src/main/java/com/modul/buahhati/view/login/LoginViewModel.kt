package com.modul.buahhati.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: UserRepository,
    private val loginPreference: LoginPreference
) : ViewModel() {
    fun login(emailInput: String, passwordInput: String)=
        userRepository.login(emailInput, passwordInput)

    fun saveState(token: String){
        viewModelScope.launch {
            loginPreference.svToken(token)
            loginPreference.login()
        }
    }
}