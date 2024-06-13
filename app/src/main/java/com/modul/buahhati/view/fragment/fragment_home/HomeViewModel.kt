package com.modul.buahhati.view.fragment.fragment_home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.data.remote.response.ArticleResponse

class HomeViewModel(private val repository: ArticleRepository) : ViewModel() {
    fun getArticles(): LiveData<Result<List<ArticleResponse>>> = repository.getArticles()
}