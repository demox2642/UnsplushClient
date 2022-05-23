package com.example.user.models

sealed class LoginState {
    data class LoginSuccess(val userAuth: UserAuth) : LoginState()
    data class LoginFailed(val error: Throwable) : LoginState()
}