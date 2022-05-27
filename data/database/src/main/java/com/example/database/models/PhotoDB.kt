package com.example.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.database.contracts.*

@Entity(
    tableName = PhotoDBContracts.TABLE_NAME,

)

data class PhotoDB(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = PhotoDBContracts.Colums.ID)
    var id: String,
    @ColumnInfo(name = PhotoDBContracts.Colums.WIDTH)
    var width: Int? = null,
    @ColumnInfo(name = PhotoDBContracts.Colums.HEIGHT)
    var height: Int? = null,
    @ColumnInfo(name = PhotoDBContracts.Colums.COLOR)
    var color: String? = null,
    @ColumnInfo(name = PhotoDBContracts.Colums.DOWNLOADS)
    var downloads: Int? = null,
    @ColumnInfo(name = PhotoDBContracts.Colums.LIKES)
    var likes: Int? = null,
    @ColumnInfo(name = PhotoDBContracts.Colums.URLS_ID)
    var urls: String? = null,
    @ColumnInfo(name = PhotoDBContracts.Colums.USER_ID)
    var user: String? = null,
    @ColumnInfo(name = PhotoDBContracts.Colums.LIKED_BY_USER)
    var likedByUser: Boolean? = null,
    @ColumnInfo(name = PhotoDBContracts.Colums.CREATED_AT)
    var createdAt: String? = null,
    @ColumnInfo(name = PhotoDBContracts.Colums.UPDATE_AT)
    var updatedAt: String? = null
)
