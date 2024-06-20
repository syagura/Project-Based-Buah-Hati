package com.modul.buahhati.view.input_pertumbuhan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.repository.UserRepository
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.data.remote.response.AnalysisResponse
import kotlinx.coroutines.flow.catch

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
    ): LiveData<Result<AnalysisResponse>> =
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
            .collect { childId ->
                emit(childId)
            }
    }
}
