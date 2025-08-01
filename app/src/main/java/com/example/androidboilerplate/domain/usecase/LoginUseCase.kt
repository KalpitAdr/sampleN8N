package com.example.androidboilerplate.domain.usecase

import com.example.androidboilerplate.domain.model.Result
import com.example.androidboilerplate.domain.model.User
import com.example.androidboilerplate.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Flow<Result<User>> {
        return authRepository.login(email, password)
    }
} 