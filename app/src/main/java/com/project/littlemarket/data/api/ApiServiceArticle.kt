package com.project.littlemarket.data.api

import com.project.littlemarket.data.model.Article
import com.project.littlemarket.utils.Constants.PATH_GET_ARTICLES
import com.project.littlemarket.utils.Constants.PATH_INSERT_ARTICLE
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiServiceArticle {
    @GET(PATH_GET_ARTICLES)
    suspend fun getArticles(): List<Article>

    @POST(PATH_INSERT_ARTICLE)
    suspend fun insertArticle(@Body article: Article): Response<Article>

    @PUT("$PATH_GET_ARTICLES/{id}")
    suspend fun updateArticle(@Path("id") id: String, @Body article: Article): Response<Article>

    @DELETE("$PATH_GET_ARTICLES/{id}")
    suspend fun deleteArticle(@Path("id") id: String): Response<Unit>
}

