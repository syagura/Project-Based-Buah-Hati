package com.modul.buahhati.view.regis_anak

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull

class RegisAnakViewModel(
    private val userRepository: UserRepository,
    private val preference: LoginPreference
): ViewModel() {
    fun regisAnak(user_id:String, nameInput:String, birthdateInput:String, genderInput:String, blood_typeInput:String, body_weightInput:Int, body_heightInput:Int, head_circumferenceInput:Int)=
        userRepository.regisAnak(user_id, nameInput, birthdateInput, genderInput, blood_typeInput, body_weightInput, body_heightInput, head_circumferenceInput)

    fun getUserId(): LiveData<String?> {
        return liveData {
            val userId = preference.getUserId().firstOrNull()
            emit(userId)
        }
    }
}

