package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.* // ktlint-disable no-wildcard-imports
import com.example.database.dao.UnsplashRemoteKeysDao
import com.example.database.models.* // ktlint-disable no-wildcard-imports
import com.example.database.models.UnsplashRemoteKeys

@Database(
    entities = [

        PhotoDB::class,
        //       ProfileImageDB::class,
//        UrlsDB::class,
        //  UserDB ::class,
        UnsplashImage::class,
        UnsplashRemoteKeys::class,
        UnsplashSearchImageKeys::class
    ],
    version = 1,
    exportSchema = false
)
abstract class UnsplashDatabase : RoomDatabase() {
    abstract fun photoDBDao(): PhotoDBDao
//    abstract fun profileImageDBDao(): ProfileImageDBDao
//    abstract fun urlsDBDao(): UrlsDBDao
    //  abstract fun userDBDao(): UserDBDao

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao
    abstract fun unsplashSearchImageKeysDao(): UnsplashSearchImageKeysDao
}
