package com.example.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.database.contracts.UserDBContracts

@Entity(
    tableName = UserDBContracts.TABLE_NAME,

)

data class UserDB(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = UserDBContracts.Colums.ID)
    var id: String,
    @ColumnInfo(name = UserDBContracts.Colums.USERNAME)
    var username: String? = null,
    @ColumnInfo(name = UserDBContracts.Colums.NAME)
    var name: String? = null,
    @ColumnInfo(name = UserDBContracts.Colums.BIO)
    var bio: String? = null,
    @ColumnInfo(name = UserDBContracts.Colums.LOCATION)
    var location: String? = null,
    @ColumnInfo(name = UserDBContracts.Colums.TOTAL_LIKES)
    var totalLikes: Int? = null,
    @ColumnInfo(name = UserDBContracts.Colums.DOWNLOADS)
    var downloads: Int? = null,
    @ColumnInfo(name = UserDBContracts.Colums.PROFILE_IMAGE_ID)
    var profileImage: String? = null,
    @ColumnInfo(name = UserDBContracts.Colums.TOTAL_PHOTOS)
    var totalPhotos: Int? = null,
    @ColumnInfo(name = UserDBContracts.Colums.TOTAL_COLLECTIONS)
    var totalCollections: Int? = null,
    @ColumnInfo(name = UserDBContracts.Colums.FOLLOWED_BY_USER)
    var followedByUser: Boolean? = null,
    @ColumnInfo(name = UserDBContracts.Colums.FOLLOWERS_COUNT)
    var followersCount: Int? = null,
    @ColumnInfo(name = UserDBContracts.Colums.FIRST_NAME)
    var firstName: String? = null,
    @ColumnInfo(name = UserDBContracts.Colums.LAST_NAME)
    var lastName: String? = null,
    @ColumnInfo(name = UserDBContracts.Colums.INSTAGRAM_USERNAME)
    var instagramUsername: String? = null,
    @ColumnInfo(name = UserDBContracts.Colums.TWITTER_USERNAME)
    var twitterUsername: String? = null,
    @ColumnInfo(name = UserDBContracts.Colums.PORTFOLIO_URL)
    var portfolioUrl: String? = null,
    @ColumnInfo(name = UserDBContracts.Colums.UPDATE_AT)
    var updatedAt: String? = null
)
