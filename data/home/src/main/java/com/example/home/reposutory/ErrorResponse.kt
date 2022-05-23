package com.example.home.reposutory

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    val error: String,
    @SerializedName("error_description") val description: String?
)
