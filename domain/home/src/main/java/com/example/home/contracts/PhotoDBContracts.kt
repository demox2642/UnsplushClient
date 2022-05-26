package com.example.home.contracts

object PhotoDBContracts {
    const val TABLE_NAME = "photo"

    object Colums {

        const val ID = "photo_id"
        const val WIDTH = "width"
        const val HEIGHT = "height"
        const val LIKES = "likes"
        const val URLS_ID = "urls_id"
        const val USER_ID = "user_id"
        const val LIKED_BY_USER = "liked_by_user"
        const val CREATED_AT = "created_at"
        const val UPDATE_AT = "updated_at"
    }
}
