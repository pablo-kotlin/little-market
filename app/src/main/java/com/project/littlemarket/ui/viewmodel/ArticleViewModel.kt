package com.project.littlemarket.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.littlemarket.data.model.Article
import com.project.littlemarket.data.repository.ArticleRepositoryImpl
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {
    private val repository = ArticleRepositoryImpl()

    val articles: LiveData<List<Article>> = repository.getArticles()

    fun insertArticle(article: Article) = viewModelScope.launch {
        repository.insertArticle(article)
    }

    fun deleteArticle(articleId: Int) = viewModelScope.launch {
        repository.deleteArticle(articleId)
    }

    fun modifyArticle(id: Int, name: String, price: Double, discount: Double) =
        viewModelScope.launch {
            // Create a new Article object with the updated values
            val updatedArticle = Article(id, name, price, discount)

            // Call the repository function to update the article in the database
            repository.updateArticle(updatedArticle)
        }

}


