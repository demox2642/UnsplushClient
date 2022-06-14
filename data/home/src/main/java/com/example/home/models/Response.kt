package com.example.home.models

data class Response<T : Any>(
    val success: String,
    val response: T? = null,
    val error: ResponseError? = null
) {
    val isSuccess
        get() = success == "true"
}
