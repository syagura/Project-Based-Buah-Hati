package com.modul.buahhati.view.regis_anak

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _updateProfile = MutableLiveData<Boolean>()
    val updateProfile: LiveData<Boolean> get() = _updateProfile

    fun setUpdateProfile(update: Boolean) {
        _updateProfile.value = update
    }
}