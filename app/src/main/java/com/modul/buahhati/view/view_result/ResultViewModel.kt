package com.modul.buahhati.view.view_result

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.repository.UserRepository
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.data.remote.response.AnalysisResultResponse

class ResultViewModel(
    private val userRepository: UserRepository,
    private val preference: LoginPreference
) : ViewModel() {

    fun getAnalysis(analysis_id: String): LiveData<Result<AnalysisResultResponse>> {
        return userRepository.getAnalysis(analysis_id)
    }
}
