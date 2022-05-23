package com.example.network.models

import java.util.ArrayList


data class Photo(
    var id: String? = null,
    var width: Int? = null,
    var height: Int? = null,
    var color: String? = null,
    var downloads: Int? = null,
    var likes: Int? = null,
    var exif: Exif? = null,
    var location: Location? = null,
    var urls: Urls? = null,
    var links: Links? = null,
    var user: User? = null,
    var categories: List<Category> = ArrayList()

)
