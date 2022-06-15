package com.example.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.contracts.PhotoDBContracts
import com.example.database.models.PhotoDB

interface CategoryDB {

    @Query("SELECT * FROM ${PhotoDBContracts.TABLE_NAME}")
    suspend fun getAllPhotoDB(): List<PhotoDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotoDB(photoDB: PhotoDB)

    @Delete
    suspend fun deletePhotoDB(photoDB: PhotoDB)
}
