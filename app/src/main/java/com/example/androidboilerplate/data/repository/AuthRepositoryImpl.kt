package com.example.androidboilerplate.data.repository

import com.example.androidboilerplate.data.remote.AuthApiService
import com.example.androidboilerplate.domain.model.Result
import com.example.androidboilerplate.domain.model.User
import com.example.androidboilerplate.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRepository {

    override suspend fun login(email: String, password: String): Flow<Result<User>> = flow {
        emit(Result.Loading)
        try {
            val loginRequest = com.example.androidboilerplate.data.model.LoginRequest(email, password)
            val response = authApiService.login(loginRequest)
            val user = User(
                id = response.userId,
                email = response.email,
                name = response.name,
                token = response.token
            )
            emit(Result.Success(user))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Login failed"))
        }
    }
} 