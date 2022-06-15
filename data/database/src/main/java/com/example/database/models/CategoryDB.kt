package com.example.database.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import com.example.database.contracts.CategoryDBContracts
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = CategoryDBContracts.TABLE_NAME)
data class CategoryDB(
    @ColumnInfo(name = CategoryDBContracts.Colums.ID)
    var id_category: Int? = null,
    @ColumnInfo(name = CategoryDBContracts.Colums.TITLE)
    var title: String? = null,
    @Embedded
    @ColumnInfo(name = CategoryDBContracts.Colums.LINKS)
    var links: LinksDB? = null,
    @ColumnInfo(name = CategoryDBContracts.Colums.PHOTO_COUNT)
    var photoCount: Int? = null
)
