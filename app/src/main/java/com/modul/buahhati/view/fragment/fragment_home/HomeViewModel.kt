package com.modul.buahhati.view.fragment.fragment_home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.data.remote.repository.UserRepository
import com.modul.buahhati.data.remote.response.ArticleResponse
import com.modul.buahhati.data.remote.response.DataItem

class HomeViewModel(private val repository: UserRepository) : ViewModel() {
    fun getArticles(): LiveData<Result<List<DataItem?>>> = repository.getArticles()
}