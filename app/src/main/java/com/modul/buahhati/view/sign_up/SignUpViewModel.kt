package com.modul.buahhati.view.sign_up

import androidx.lifecycle.ViewModel
import com.modul.buahhati.data.remote.repository.UserRepository

class SignUpViewModel(
    private val userRepository: UserRepository
): ViewModel(){
    fun signup(nameInput : String, usernameInput:String, emailInput : String, passwordInput:String) =
        userRepository.register(nameInput, usernameInput, emailInput, passwordInput)
}