package com.example.user.models

import com.google.gson.annotations.SerializedName

data class Token(
    var scope: String,
    @SerializedName("access_token") var accessToken: String,
    @SerializedName("token_type") var tokenType: String,
    @SerializedName("created_at") var createdAt: Int
)
