package com.project.littlemarket.data.api

import com.project.littlemarket.data.model.User
import com.project.littlemarket.utils.Constants
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiServiceUser {
    @GET(Constants.PATH_GET_USERS)
    suspend fun getUsers(): List<User>

    @POST(Constants.PATH_GET_USERS)
    suspend fun insertUser(@Body user: User): ResponseBody

    @PUT(Constants.PATH_GET_USERS + "/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body user: User): ResponseBody

    @DELETE(Constants.PATH_GET_USERS + "/{id}")
    suspend fun deleteUser(@Path("id") id: String): ResponseBody
}