package com.modul.buahhati.view.view_result

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.repository.UserRepository
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.data.remote.response.AnalysisResultResponse
import com.modul.buahhati.data.remote.response.ResultResponse

class ResultViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    fun getAnalysis(child_id: String, analysis_id: String): LiveData<Result<ResultResponse>> {
        return userRepository.getAnalysis(child_id, analysis_id)
    }
}
