package com.example.home.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.home.contracts.PhotoRemoteKeysDBContracts

@Entity(tableName = PhotoRemoteKeysDBContracts.TABLE_NAME)
data class UnsplashRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)