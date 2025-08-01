package com.example.androidboilerplate.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidboilerplate.domain.model.Result
import com.example.androidboilerplate.domain.model.User
import com.example.androidboilerplate.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow<Result<User>>(Result.Idle)
    val loginState: StateFlow<Result<User>> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(email, password).collect { result ->
                _loginState.value = result
            }
        }
    }

    fun resetState() {
        _loginState.value = Result.Idle
    }
} 