package com.example.database.models

import kotlinx.serialization.Serializable

@Serializable
data class UrlsDB(
    var raw: String? = null,
    var full: String? = null,
    var regular: String,
    var small: String? = null,
    var thumb: String? = null
)
