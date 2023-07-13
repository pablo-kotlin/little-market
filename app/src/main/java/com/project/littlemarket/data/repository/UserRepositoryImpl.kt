package com.project.littlemarket.data.repository

import androidx.lifecycle.liveData
import com.project.littlemarket.data.api.ApiClient
import kotlinx.coroutines.Dispatchers

class UserRepositoryImpl : UserRepository {
    override fun getUsers() = liveData(Dispatchers.IO) {
        try {
            val users = ApiClient.userService.getUsers()
            emit(users)
        } catch (exception: Exception) {
            // Manejar excepciones
        }
    }
}
