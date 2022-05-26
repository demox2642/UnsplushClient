package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.*
import com.example.home.models.ProfileImage
import com.example.home.models.UnsplashImage
import com.example.home.models.UnsplashRemoteKeys
import com.example.home.models.User

@Database(
    entities = [
        UnsplashRemoteKeys::class,
        UnsplashImage::class,
//        ProfileImage::class,
//        User::class,
//        UserDB ::class
    ],
    version = 1
)
abstract class UnsplashDatabase : RoomDatabase() {

    abstract fun photoRemoteKeysDBDao(): PhotoRemoteKeysDBDao
    abstract fun photoDBDao(): PhotoDBDao
//    abstract fun urlsDBDao(): UrlsDBDao
//    abstract fun userDBDao(): UserDBDao
}
