package com.example.network.models

import com.google.gson.annotations.SerializedName

data class CoverPhoto(
    var id: String? = null,
    var width: Int? = null,
    var height: Int? = null,
    var color: String? = null,
    var likes: Int? = null,
    var user: User? = null,
    var urls: Urls? = null,
    var categories: List<Category?> = ArrayList(),
    var links: Links? = null,
    @SerializedName("liked_by_user") var likedByUser: Boolean? = null
)
