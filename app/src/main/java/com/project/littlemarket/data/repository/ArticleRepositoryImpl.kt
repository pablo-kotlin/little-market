package com.project.littlemarket.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import com.project.littlemarket.data.api.ApiClient
import kotlinx.coroutines.Dispatchers

class ArticleRepositoryImpl : ArticleRepository {
    override fun getArticles() = liveData(Dispatchers.IO) {
        try {
            val articles = ApiClient.articleService.getArticles()
            emit(articles)
        } catch (exception: Exception) {
            Log.e("ERROR", exception.toString())
        }
    }
}
