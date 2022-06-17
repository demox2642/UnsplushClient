package com.example.home.models

import com.google.gson.annotations.SerializedName

data class DetailPhoto(
    var id: String? = null,
    var width: Int? = null,
    var height: Int? = null,
    var color: String? = null,
    var downloads: Int? = null,
    var likes: Int? = null,
    var location: LocationDomain? = null,
    var urlsPhoto: String? = null,
    var linksDomain: LinksDomain? = null,
    var userDomain: UserDomain? = null,
    var categories: String? = null,
    var description: String? = null,
    var downloadLink: String? = null,
    var exifDomain: ExifDomain? = null,

    @SerializedName("liked_by_user") var likedByUser: Boolean? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    var code: Int? = null,
    var errorMessage: String ? = null

)
