package com.example.home.models

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class DetailPhoto(
    var id: String? = null,
    var width: Int? = null,
    var height: Int? = null,
    var color: String? = null,
    var downloads: Int? = null,
    var likes: Int? = null,
    var location: LocationDomain? = null,
    var urlsDomain: UrlsDomain? = null,
    var linksDomain: LinksDomain? = null,
    var userDomain: UserDomain? = null,
    var categories: List<CategoryDomain> = ArrayList(),
    var description: String? = null,

    @SerializedName("liked_by_user") var likedByUser: Boolean? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)
