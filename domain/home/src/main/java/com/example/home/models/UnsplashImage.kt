package com.example.home.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.home.contracts.PhotoDBContracts
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = PhotoDBContracts.TABLE_NAME)
data class UnsplashImage(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    var downloads: Int?,
    @Embedded
    val urls: Urls,
    val likes: Int?,
    @Embedded
    val user: User
)
