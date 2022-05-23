package com.example.database.contracts

object PhotoDBContracts {
    const val TABLE_NAME = "photo"

    object Colums {

        const val ID = "id"
        const val WIDTH = "width"
        const val HEIGHT = "height"
        const val COLOR = "color"
        const val DOWNLOADS = "downloads"
        const val LIKES = "likes"
        const val LOCATION_ID = "location_id"
        const val URLS_ID = "urls_id"
        const val USER_ID = "user_id"
        const val LIKED_BY_USER = "liked_by_user"
        const val CREATED_AT = "created_at"
        const val UPDATE_AT = "updated_at"
    }
}
