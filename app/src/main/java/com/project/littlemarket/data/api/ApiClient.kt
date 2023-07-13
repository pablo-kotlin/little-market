package com.project.littlemarket.data.api

import com.project.littlemarket.utils.PrivateConstants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val articleService: ApiServiceArticle by lazy {
        retrofit.create(ApiServiceArticle::class.java)
    }

    val userService: ApiServiceUser by lazy {
        retrofit.create(ApiServiceUser::class.java)
    }
}


