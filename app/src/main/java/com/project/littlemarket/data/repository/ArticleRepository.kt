package com.project.littlemarket.data.repository

import androidx.lifecycle.LiveData
import com.project.littlemarket.data.model.Article

interface ArticleRepository {
    fun getArticles(): LiveData<List<Article>>
    suspend fun insertArticle(article: Article)

}
