package com.example.services

import com.example.user.models.Constants.SPLASH_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return try {
            val modifiedRequest = request.newBuilder()
                .addHeader("Accept-Version", "v1")
                .addHeader("Authorization", SPLASH_KEY)
                .build()
            chain.proceed(modifiedRequest)
        } catch (e: Exception) {
            chain.proceed(request)
        }
    }
}
