package com.example.database.contracts

object UnsplashImageDBContracts {

    const val TABLE_NAME = "unsplash_image"

    object Colums {

        const val ID = "id"

        const val DESCRIPTION = "description"
        const val URLS = "urls"
        const val LIKES = "likes"
        const val USER = "user"
        const val LIKEDBYUSER = "likedByUser"
        const val WIDTH = "width"
        const val HEIGHT = "height"
        const val COLOR = "color"
        const val DOWNLOADS = "downloads"
        const val LOCATION = "location"
        const val CATEGORIES = "categories"
        const val EXIF = "exif"
        const val CREATEDAT = "createdAt"
        const val UPDATEDAT = "updatedAt"
    }
}
