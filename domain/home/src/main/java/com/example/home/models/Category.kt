package com.example.home.models

import com.google.gson.annotations.SerializedName

data class Category(
    var id: Int? = null,
    var title: String? = null,
    var links: Links? = null,
    @SerializedName("photo_count") var photoCount: Int? = null
)
