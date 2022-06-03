package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.*
import com.example.database.dao.UnsplashRemoteKeysDao
import com.example.database.models.*
import com.example.database.models.UnsplashRemoteKeys

@Database(
    entities = [

        PhotoDB::class,
        ProfileImageDB::class,
        UrlsDB::class,
        UserDB ::class,
        UnsplashImage::class,
        UnsplashRemoteKeys::class,
        UnsplashSearchImageKeys::class
    ],
    version = 1
)
abstract class UnsplashDatabase : RoomDatabase() {
    abstract fun photoDBDao(): PhotoDBDao
    abstract fun profileImageDBDao(): ProfileImageDBDao
    abstract fun urlsDBDao(): UrlsDBDao
    abstract fun userDBDao(): UserDBDao

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao
    abstract fun unsplashSearchImageKeysDao(): UnsplashSearchImageKeysDao
}
