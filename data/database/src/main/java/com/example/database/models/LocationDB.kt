package com.example.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.database.contracts.LocationDBContracts

@Entity(
    tableName = LocationDBContracts.TABLE_NAME
)

data class LocationDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = LocationDBContracts.Colums.ID)
    var id: Long?,
    @ColumnInfo(name = LocationDBContracts.Colums.CITY)
    var city: String? = null,
    @ColumnInfo(name = LocationDBContracts.Colums.COUNTRY)
    var country: String? = null
)
