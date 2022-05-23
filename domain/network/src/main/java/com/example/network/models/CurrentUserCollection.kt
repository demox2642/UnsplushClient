package com.example.network.models

import com.google.gson.annotations.SerializedName

data class CurrentUserCollection(
    var id: Int? = null,
    var title: String? = null,
    var curated: Boolean? = null,
    @SerializedName("user") var user: User? = null,
    @SerializedName("cover_photo") var coverPhoto: CoverPhoto? = null,
    @SerializedName("published_at") var publishedAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
)
