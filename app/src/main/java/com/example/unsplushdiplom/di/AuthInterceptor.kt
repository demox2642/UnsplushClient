package com.example.unsplushdiplom.di

import com.example.network.Constants
import com.example.user.repository.UserRepository
import com.example.user.usecase.auth.GetTokenUserCase
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val userRepository: UserRepository
) : Interceptor {
    private val token = userRepository.getToken()
    private val authorization = "Authorization"
    private val bearer = "Bearer "
    private val clientId = "client_id"
    override fun intercept(chain: Interceptor.Chain): Response {
        if (token.isNotEmpty()) {
            val request = chain.request()
                .newBuilder()
                .addHeader(authorization, bearer + token)
                .build()
            return chain.proceed(request)
        } else {
            val newHttpurl = chain.request().url().newBuilder()
                .addQueryParameter(clientId, Constants.SPLASH_KEY)
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newHttpurl)
                .build()

            return chain.proceed(newRequest)
        }
    }
}
