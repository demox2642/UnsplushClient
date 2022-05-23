package com.example.services


import com.example.user.models.Token
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface AuthService {

    @POST
    suspend fun getTokenSuspend(
        @Url url: String,
        @Query("client_id") clientID: String,
        @Query("client_secret") clientSecret: String,
        @Query("redirect_uri") redirectURI: String,
        @Query("code") code: String,
        @Query("grant_type") grantType: String
    ): Token
}
