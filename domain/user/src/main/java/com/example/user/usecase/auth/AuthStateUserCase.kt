package com.example.user.usecase.auth

import com.example.user.models.Token
import com.example.user.repository.AuthRepository

class AuthStateUserCase(private val authRepository: AuthRepository) {

    suspend fun getTokenSuspend(
        url: String,
        clientID: String,
        clientSecret: String,
        redirectURI: String,
        code: String,
        grantType: String
    ): Token {
        return authRepository.getTokenSuspend(url, clientID, clientSecret, redirectURI, code, grantType)
    }
}
