package com.example.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.database.contracts.LocationDBContracts
import com.example.database.contracts.ProfileImageDBContracts

@Entity(
    tableName = ProfileImageDBContracts.TABLE_NAME,
    indices = [
        Index(
            value = [
                ProfileImageDBContracts.Colums.SMALL,
                ProfileImageDBContracts.Colums.MEDIUM,
                ProfileImageDBContracts.Colums.LARGE
            ],
            unique = true
        )
    ]
)

data class ProfileImageDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ProfileImageDBContracts.Colums.ID)
    var id: Long?,
    @ColumnInfo(name = ProfileImageDBContracts.Colums.SMALL)
    var small: String? = null,
    @ColumnInfo(name = ProfileImageDBContracts.Colums.MEDIUM)
    var medium: String? = null,
    @ColumnInfo(name = ProfileImageDBContracts.Colums.LARGE)
    var large: String? = null
)
