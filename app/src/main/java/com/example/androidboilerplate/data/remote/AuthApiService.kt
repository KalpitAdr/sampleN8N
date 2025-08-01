package com.example.androidboilerplate.data.remote

import com.example.androidboilerplate.data.model.LoginRequest
import com.example.androidboilerplate.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
} 