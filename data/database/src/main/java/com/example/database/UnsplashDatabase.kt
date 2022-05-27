package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.*
import com.example.database.models.*

@Database(
    entities = [

        PhotoDB::class,
        ProfileImageDB::class,
        UrlsDB::class,
        UserDB ::class
    ],
    version = 1
)
abstract class UnsplashDatabase : RoomDatabase() {
    abstract fun photoDBDao(): PhotoDBDao
    abstract fun profileImageDBDao(): ProfileImageDBDao
    abstract fun urlsDBDao(): UrlsDBDao
    abstract fun userDBDao(): UserDBDao
}
