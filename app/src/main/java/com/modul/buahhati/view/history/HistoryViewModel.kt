package com.modul.buahhati.view.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.repository.UserRepository
import com.modul.buahhati.data.remote.response.ResultData
import com.modul.buahhati.data.remote.Result

class HistoryViewModel(
    private val userRepository: UserRepository,
    private val preference: LoginPreference
) : ViewModel() {

    fun getHistory(child_id: String): LiveData<Result<List<ResultData>>> =
        userRepository.getAllAnalysis(child_id).also {
            it.observeForever { result ->
                when (result) {
                    is Result.Success -> Log.d("HistoryViewModel", "Data received: ${result.data}")
                    is Result.Error -> Log.e("HistoryViewModel", "Error: ${result.error}")
                    is Result.Loading -> Log.d("HistoryViewModel", "Loading data...")
                }
            }
        }
}