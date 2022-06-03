package com.example.user.repository

import com.example.user.models.Token

interface UserRepository {
    fun getAuthState(): String
    fun saveOnboardingState()
    fun getOnboardingState(): Boolean = false
    fun saveToken(token: Token)
    fun getToken(): String
}
