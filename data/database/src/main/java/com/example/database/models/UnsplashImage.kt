package com.example.database.models

import androidx.room.* // ktlint-disable no-wildcard-imports
import com.example.database.contracts.UnsplashImageDBContracts
import kotlinx.serialization.Serializable

@Serializable
@Entity(
    tableName = UnsplashImageDBContracts.TABLE_NAME,
//    foreignKeys = arrayOf(
//        ForeignKey(
//            entity = CategoryDB::class,
//            parentColumns = arrayOf("id"),
//            childColumns = arrayOf("id_category")
//        )
//    )
)
data class UnsplashImage(

    @PrimaryKey(autoGenerate = false)
    val id: String,
    val description: String?,
    @Embedded
    val urls: UrlsDB,
    val likes: Int,
    @Embedded
    val user: UserDB,
    val likedByUser: Boolean,
    var width: Int? = null,
    var height: Int? = null,
    var color: String? = null,
    var downloads: Int? = null,
    @Embedded
    var location: LocationDB? = null,
    var categories: String? = null,
    @Embedded
    var exif: ExifDB? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null
)
