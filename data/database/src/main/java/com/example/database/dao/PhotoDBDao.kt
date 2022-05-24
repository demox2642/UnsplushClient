package com.example.database.dao

import androidx.room.*
import com.example.database.contracts.PhotoDBContracts
import com.example.database.models.PhotoDB

@Dao
interface PhotoDBDao {
    @Query("SELECT * FROM ${PhotoDBContracts.TABLE_NAME}")
    suspend fun getAllEmployers(): List<PhotoDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployers(employers: List<PhotoDB>)

    @Delete
    suspend fun deleteEmployers(employers: PhotoDB)
}
