package com.modul.buahhati.view.view_result

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.repository.UserRepository
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.data.remote.response.AnalysisResultResponse
import kotlinx.coroutines.flow.catch

class ResultViewModel(
    private val userRepository: UserRepository,
    private val preference: LoginPreference
) : ViewModel() {

    fun getAnalysis(analysis_id: String): LiveData<Result<List<AnalysisResultResponse>>> {
        return liveData {
            emit(Result.Loading)
            try {
                val response = userRepository.getAnalysis(analysis_id)
                emit(response)
            } catch (e: Exception) {
                Log.e("ResultViewModel", "Error loading analysis data", e)
                emit(Result.Error(e.message ?: "ViewModel error"))
            }
        }
    }

    fun getAnalysisID(): LiveData<String?> = liveData {
        preference.getUserId()
            .catch { e ->
                emit(null)
            }
            .collect{analysis_id ->
                emit(analysis_id)
            }
    }
}
