package com.modul.buahhati.view.fragment.fragment_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modul.buahhati.data.remote.LoginPreference
import kotlinx.coroutines.launch

class ProfileViewModel( private val preference: LoginPreference) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            preference.logout()
        }
    }
}