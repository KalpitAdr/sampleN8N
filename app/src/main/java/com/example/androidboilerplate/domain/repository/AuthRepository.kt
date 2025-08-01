package com.example.androidboilerplate.domain.repository

import com.example.androidboilerplate.domain.model.Result
import com.example.androidboilerplate.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<Result<User>>
} 