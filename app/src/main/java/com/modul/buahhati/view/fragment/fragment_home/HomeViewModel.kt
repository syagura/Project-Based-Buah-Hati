package com.modul.buahhati.view.fragment.fragment_home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.modul.buahhati.data.remote.LoginPreference
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.data.remote.repository.UserRepository
import com.modul.buahhati.data.remote.response.DataItem
import kotlinx.coroutines.flow.catch

class HomeViewModel(
    private val repository: UserRepository,
    private val preference: LoginPreference
) : ViewModel() {
    fun getArticles(): LiveData<Result<List<DataItem?>>> = repository.getArticles()

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