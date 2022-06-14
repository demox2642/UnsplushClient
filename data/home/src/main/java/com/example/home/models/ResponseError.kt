package com.example.home.models

import com.google.gson.annotations.SerializedName

data class ResponseError(
    @SerializedName("error_code")
    val errorCode: Int,
    @SerializedName("error_msg")
    val errorMessage: String,
    @SerializedName("error_data")
    val errorData: Map<String, String> = emptyMap()
) : Exception(errorMessage)
