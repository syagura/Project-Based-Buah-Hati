package com.modul.buahhati.view.input_pertumbuhan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.repository.UserRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull

class InputPertumbuhanViewModel(
    private val userRepository: UserRepository,
    private val preference: LoginPreference
) : ViewModel() {

    fun addData(
        child_id: String,
        dateInput: String,
        ageInput: Int,
        genderInput: String,
        weightInput: Int,
        heightInput: Int,
        headCircumferenceInput: Int
    ) =
        userRepository.addDataPertumbuhan(
            child_id,
            dateInput,
            ageInput,
            genderInput,
            weightInput,
            heightInput,
            headCircumferenceInput
        )

    fun getChildID(): LiveData<String?> = liveData {
        preference.getUserId()
            .catch { e ->
                emit(null)
            }
            .collect{childId ->
                emit(childId)
            }
    }
}