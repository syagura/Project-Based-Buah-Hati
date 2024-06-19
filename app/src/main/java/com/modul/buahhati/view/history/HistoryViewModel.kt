package com.modul.buahhati.view.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.modul.buahhati.data.remote.repository.UserRepository
import com.modul.buahhati.data.remote.response.ResultData
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.data.remote.response.AnalysisResultResponse

class HistoryViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    fun getHistory(): LiveData<Result<List<AnalysisResultResponse>>> =
        userRepository.getAllAnalysis().also {
            it.observeForever { result ->
                when (result) {
                    is Result.Success -> Log.d("HistoryViewModel", "Data received: ${result.data}")
                    is Result.Error -> Log.e("HistoryViewModel", "Error: ${result.error}")
                    is Result.Loading -> Log.d("HistoryViewModel", "Loading data...")
                }
            }
        }
}