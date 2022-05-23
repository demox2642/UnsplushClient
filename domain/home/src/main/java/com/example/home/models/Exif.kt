package com.example.home.models

import com.google.gson.annotations.SerializedName

data class Exif(
    var make: String? = null,
    var model: String? = null,
    var aperture: String? = null,
    var iso: Int? = null,
    @SerializedName("exposure_time") var exposureTime: String? = null,
    @SerializedName("focal_length") var focalLength: String? = null
) 
