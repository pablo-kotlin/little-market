package com.project.littlemarket.data.repository

import androidx.lifecycle.LiveData
import com.project.littlemarket.data.model.User

interface UserRepository {
    fun getUsers(): LiveData<List<User>>
}