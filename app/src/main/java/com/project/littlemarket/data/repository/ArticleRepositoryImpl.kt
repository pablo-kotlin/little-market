package com.project.littlemarket.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import com.project.littlemarket.data.api.ApiClient
import com.project.littlemarket.data.model.Article
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

    override suspend fun insertArticle(article: Article) {
        try {
            val response = ApiClient.articleService.insertArticle(article)
            if (response.isSuccessful) {
                Log.d("Repository", "Article inserted successfully")
            } else {
                Log.e("Repository", "Failed to insert article: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.e("Repository", "Failed to insert article: ${e.message}")
        }
    }

    override suspend fun deleteArticle(articleId: Int) {
        try {
            val response = ApiClient.articleService.deleteArticle(articleId)
            if (response.isSuccessful) {
                Log.d("Repository", "Article deleted successfully")
            } else {
                Log.e("Repository", "Failed to delete article: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.e("Repository", "Failed to delete article: ${e.message}")
        }
    }

    override suspend fun updateArticle(article: Article) {
        try {
            val response = ApiClient.articleService.updateArticle(
                article.id,
                article.name,
                article.price,
                article.discount
            )
            if (response.isSuccessful) {
                Log.d("Repository", "Article updated successfully")
            } else {
                Log.e("Repository", "Failed to update article: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.e("Repository", "Failed to update article: ${e.message}")
        }
    }

}
