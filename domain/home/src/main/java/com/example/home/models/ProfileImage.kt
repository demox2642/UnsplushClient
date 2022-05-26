package com.example.home.models

import androidx.room.Entity
import com.example.home.contracts.DBContracts.PROFILE_IMAGE_TABLE_NAME
import kotlinx.serialization.Serializable

@Serializable
//@Entity(tableName = PROFILE_IMAGE_TABLE_NAME)
data class ProfileImage(
    var small: String? = null,
    var medium: String? = null,
    var large: String? = null
)
