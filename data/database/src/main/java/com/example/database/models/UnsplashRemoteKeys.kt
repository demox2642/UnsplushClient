package com.example.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.database.contracts.UnsplushRemoteKeyContracts

@Entity(tableName = UnsplushRemoteKeyContracts.TABLE_NAME)
data class UnsplashRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
