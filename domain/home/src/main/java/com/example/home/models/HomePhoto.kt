package com.example.home.models

data class HomePhoto(

    var id: String,
    var likes: Int? = null,
    var urls_regular: String? = null,
    var user_name: String? = null,
    var user_fio: String? = null,
    var user_img: String? = null,
    var likedByUser: Boolean? = null
)
