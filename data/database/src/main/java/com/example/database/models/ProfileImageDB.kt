package com.example.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.database.contracts.ProfileImageDBContracts

@Entity(
    tableName = ProfileImageDBContracts.TABLE_NAME,

)

data class ProfileImageDB(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = ProfileImageDBContracts.Colums.ID)
    var id_prof_im: String,
    @ColumnInfo(name = ProfileImageDBContracts.Colums.SMALL)
    var small: String? = null,
    @ColumnInfo(name = ProfileImageDBContracts.Colums.MEDIUM)
    var medium: String? = null,
    @ColumnInfo(name = ProfileImageDBContracts.Colums.LARGE)
    var large: String? = null
)
