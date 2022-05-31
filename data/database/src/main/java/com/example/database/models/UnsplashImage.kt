package com.example.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.database.contracts.UnsplashImageDBContracts
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = UnsplashImageDBContracts.TABLE_NAME)
data class UnsplashImage(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @Embedded
    val urls: Urls,
    val likes: Int,
    @Embedded
    val user: User
)
