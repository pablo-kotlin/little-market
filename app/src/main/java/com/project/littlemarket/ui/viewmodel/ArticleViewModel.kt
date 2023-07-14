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
}

