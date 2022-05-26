package com.example.home.models

import androidx.room.Embedded
import androidx.room.Entity
import com.example.home.contracts.DBContracts.USER_TABLE_NAME
import com.example.home.contracts.PhotoDBContracts
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
//@Entity(tableName = USER_TABLE_NAME)
data class User(
    @SerialName("links")
    @Embedded
    val userLinks: UserLinks?,
    val username: String?,
    @Embedded
    @SerializedName("profile_image")
    val profileImage: ProfileImage?
)
