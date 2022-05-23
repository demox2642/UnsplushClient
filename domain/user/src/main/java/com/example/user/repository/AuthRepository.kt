package com.example.user.repository

import com.example.user.models.Token

interface AuthRepository {

    suspend fun getTokenSuspend(
        url: String,
        clientID: String,
        clientSecret: String,
        redirectURI: String,
        code: String,
        grantType: String
    ): Token
}
