package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.database.contracts.LocationDBContracts
import com.example.database.models.LocationDB

@Dao
interface LocationDBDao {
    @Query("SELECT * FROM ${LocationDBContracts.TABLE_NAME}")
    suspend fun getAllEmployers(): List<LocationDB>

    @Insert
    suspend fun insertEmployers(employers: List<LocationDB>)

    @Delete
    suspend fun deleteEmployers(employers: LocationDB)
}
