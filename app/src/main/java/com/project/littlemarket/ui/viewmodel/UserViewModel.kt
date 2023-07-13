package com.project.littlemarket.ui.viewmodel

import androidx.lifecycle.LiveData
import com.project.littlemarket.data.model.User
import com.project.littlemarket.data.repository.UserRepositoryImpl

class UserViewModel {
    private val repository = UserRepositoryImpl()
    val user: LiveData<List<User>> = repository.getUsers()
}