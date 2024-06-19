package com.modul.buahhati.view.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.modul.buahhati.data.remote.repository.UserRepository
import com.modul.buahhati.data.remote.response.ResultData
import com.modul.buahhati.data.remote.Result

class HistoryViewModel(
    private val userRepository: UserRepository
) : ViewModel(){

    fun getHistory() : LiveData<Result<List<ResultData>>> =
        userRepository.getAllAnalysis()
}