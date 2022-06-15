package com.example.database.models

import androidx.room.Embedded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDB(
    @SerialName("linksDomain")
    var username: String? = null,
    var name: String? = null,
    var bio: String? = null,
    var location_user: String? = null,
    var totalLikes: Int? = null,
    var downloads_user: Int? = null,
    @Embedded
    var profileImage: ProfileImageDB?,
    var totalPhotos: Int? = null,
    @Embedded
    val userLinks: UserLinks,
    var totalCollections: Int? = null,
    var followedByUser: Boolean? = null,
    var followersCount: Int? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var instagramUsername: String? = null,
    var twitterUsername: String? = null,
    var portfolioUrl: String? = null,
    var updatedAt_user: String? = null
)
