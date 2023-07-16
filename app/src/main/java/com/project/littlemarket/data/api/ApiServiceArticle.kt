package com.project.littlemarket.data.api

import com.project.littlemarket.data.model.Article
import com.project.littlemarket.utils.Constants.PATH_DELETE_ARTICLE
import com.project.littlemarket.utils.Constants.PATH_GET_ARTICLES
import com.project.littlemarket.utils.Constants.PATH_INSERT_ARTICLE
import com.project.littlemarket.utils.Constants.PATH_UPDATE_ARTICLE
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServiceArticle {
    @GET(PATH_GET_ARTICLES)
    suspend fun getArticles(): List<Article>

    @POST(PATH_INSERT_ARTICLE)
    suspend fun insertArticle(@Body article: Article): Response<Article>

    @FormUrlEncoded
    @POST(PATH_UPDATE_ARTICLE)
    suspend fun updateArticle(
        @Field("id") id: Int,
        @Field("name") name: String,
        @Field("price") price: Double,
        @Field("discount") discount: Double
    ): Response<Article>

    @FormUrlEncoded
    @POST(PATH_DELETE_ARTICLE)
    suspend fun deleteArticle(@Field("id") id: Int): Response<Unit>

}

