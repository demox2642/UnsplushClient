package com.example.home.models

import com.google.gson.annotations.SerializedName

data class Links(
    var self: String? = null,
    var html: String? = null,
    var photos: String? = null,
    var likes: String? = null,
    var portfolio: String? = null,
    var download: String? = null,
    @SerializedName("download_location") var downloadLocation: String? = null
) 
