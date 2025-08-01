package com.example.androidboilerplate.data.model

data class LoginResponse(
    val token: String,
    val userId: String,
    val email: String,
    val name: String
) 