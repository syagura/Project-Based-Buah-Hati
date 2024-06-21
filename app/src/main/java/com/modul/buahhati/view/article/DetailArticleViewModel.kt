package com.modul.buahhati.view.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.modul.buahhati.data.remote.Result
import com.modul.buahhati.data.remote.repository.UserRepository
import com.modul.buahhati.data.remote.response.DataItem

class DetailArticleViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getArticleById(articleId: String): LiveData<Result<DataItem>> = liveData {
        emit(Result.Loading)
        try {
            val articlesResult = userRepository.getArticles().value
            val article = (articlesResult as? Result.Success)?.data?.find { it?.articleId == articleId }
            if (article != null) {
                emit(Result.Success(article))
            } else {
                emit(Result.Error("Article not found"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
}