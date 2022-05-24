package com.example.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.database.contracts.BadgeDBContracts

@Entity(
    tableName = BadgeDBContracts.TABLE_NAME,
    indices = [
        Index(
            value = [
                BadgeDBContracts.Colums.TITLE,
                BadgeDBContracts.Colums.LINK
            ],
            unique = true
        )
    ]
)
data class BadgeDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = BadgeDBContracts.Colums.ID)
    var id: Long?,
    @ColumnInfo(name = BadgeDBContracts.Colums.TITLE)
    var title: String,
    @ColumnInfo(name = BadgeDBContracts.Colums.PRIMARY)
    var primary: Boolean,
    @ColumnInfo(name = BadgeDBContracts.Colums.SLUG)
    var slug: String,
    @ColumnInfo(name = BadgeDBContracts.Colums.LINK)
    var link: String
)
