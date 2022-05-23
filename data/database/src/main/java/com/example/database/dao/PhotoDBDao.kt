package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.database.contracts.PhotoDBContracts
import com.example.database.models.PhotoDB

@Dao
interface PhotoDBDao {
    @Query("SELECT * FROM ${PhotoDBContracts.TABLE_NAME}")
    suspend fun getAllEmployers(): List<PhotoDB>

    @Insert
    suspend fun insertEmployers(employers: List<PhotoDB>)

    @Delete
    suspend fun deleteEmployers(employers: PhotoDB)
}
