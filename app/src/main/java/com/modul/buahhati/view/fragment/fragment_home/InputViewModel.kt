package com.modul.buahhati.view.fragment.fragment_home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull

class InputViewModel(
    private val userRepository: UserRepository,
    private val preference: LoginPreference
) : ViewModel() {

    fun addData(child_id: String, dateInput: String, ageInput: Int, genderInput: String, weightInput: Int, heightInput: Int, headCircumferenceInput: Int) =
        userRepository.addDataPertumbuhan(child_id, dateInput, ageInput, genderInput, weightInput, heightInput, headCircumferenceInput
        )

    fun getChildID():LiveData<String?>{
        return liveData {
            val childID = preference.getchildId().firstOrNull()
            emit(childID)
        }
    }
}