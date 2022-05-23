package com.example.network.models

import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("total_photos") var totalPhotos: Int? = null,
    @SerializedName("photo_downloads") var photoDownloads: Int? = null
)
