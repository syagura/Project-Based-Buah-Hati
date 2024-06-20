package com.modul.buahhati.view.detail_history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.modul.buahhati.data.remote.repository.UserRepository
import com.modul.buahhati.data.remote.response.AnalysisResultResponse
import com.modul.buahhati.data.remote.Result

class DetailHistoryViewModel(private val userRepository: UserRepository) : ViewModel() {

//    fun getAnalysisDetail(analysisId: String): LiveData<Result<AnalysisResultResponse>> =
//        userRepository.getAnalysis(analysisId)
}
