package com.example.home.models

import com.google.gson.annotations.SerializedName

data class CategoryDomain(
    var id: Int? = null,
    var title: String? = null,
    var linksDomain: LinksDomain? = null,
    @SerializedName("photo_count") var photoCount: Int? = null
)
