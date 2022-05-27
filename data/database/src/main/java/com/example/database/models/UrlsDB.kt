package com.example.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.database.contracts.UrlsDBContracts

@Entity(
    tableName = UrlsDBContracts.TABLE_NAME,

)

data class UrlsDB(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = UrlsDBContracts.Colums.ID)
    var id: String,
    @ColumnInfo(name = UrlsDBContracts.Colums.RAW)
    var raw: String? = null,
    @ColumnInfo(name = UrlsDBContracts.Colums.FULL)
    var full: String? = null,
    @ColumnInfo(name = UrlsDBContracts.Colums.REGULAR)
    var regular: String? = null,
    @ColumnInfo(name = UrlsDBContracts.Colums.SMALL)
    var small: String? = null,
    @ColumnInfo(name = UrlsDBContracts.Colums.THUMB)
    var thumb: String? = null
)
