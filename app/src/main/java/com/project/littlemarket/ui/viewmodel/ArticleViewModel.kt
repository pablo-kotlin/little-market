package com.project.littlemarket.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.project.littlemarket.data.model.Article
import com.project.littlemarket.data.repository.ArticleRepositoryImpl

class ArticleViewModel : ViewModel() {
    private val repository = ArticleRepositoryImpl()
    val articles: LiveData<List<Article>> = repository.getArticles()
}

