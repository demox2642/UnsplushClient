package com.example.home.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Photo(
    var id: String? = null,
    var width: Int? = null,
    var height: Int? = null,
    var color: String? = null,
    var downloads: Int? = null,
    var likes: Int? = null,
    var location: Location? = null,
    var urls: Urls? = null,
    var links: Links? = null,
    var user: User,
    var categories: List<Category> = ArrayList(),
    var description: String? = null,
    var exif: Exif? = null,

    @SerializedName("liked_by_user") var likedByUser: Boolean? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)
