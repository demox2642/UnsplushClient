package com.example.user.repository

import com.example.services.AuthService
import com.example.user.models.Token
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthService
) : AuthRepository {

    override suspend fun getTokenSuspend(
        url: String,
        clientID: String,
        clientSecret: String,
        redirectURI: String,
        code: String,
        grantType: String,
    ): Token {
        return api.getTokenSuspend(url, clientID, clientSecret, redirectURI, code, grantType)
    }
}
