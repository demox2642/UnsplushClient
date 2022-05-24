package com.example.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.database.contracts.BadgeDBContracts
import com.example.database.contracts.LocationDBContracts

@Entity(
    tableName = LocationDBContracts.TABLE_NAME,
    indices = [
        Index(
            value = [
                LocationDBContracts.Colums.CITY,
                LocationDBContracts.Colums.COUNTRY
            ],
            unique = true
        )
    ]
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
