package com.example.database.models

data class ProfileImageDB(
    var id_prof_im: String,
    var small_im: String? = null,
    var medium: String? = null,
    var large: String? = null
)
