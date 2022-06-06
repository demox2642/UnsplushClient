package com.example.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.database.contracts.UnsplushRemoteKeyContracts

@Entity(tableName = UnsplushRemoteKeyContracts.SEARCH_TABLE_NAME)
data class UnsplashSearchImageKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
