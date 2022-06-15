package com.example.database.models

data class ExifDB(
    var make: String? = null,
    var model: String? = null,
    var aperture: String? = null,
    var iso: Int? = null,
    var exposureTime: String? = null,
    var focalLength: String? = null
)
