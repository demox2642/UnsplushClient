package com.example.onboarding.model

/**
 * Class used for API responses
 */

sealed class UnsplashResource<out T>(
    val data: T? = null,
    val errorMessage: String
) {
    class Success<T>(data: T?) : UnsplashResource<T>(data, "")
    class Error<T>(errorMessage: String) : UnsplashResource<T>(null, errorMessage)
}