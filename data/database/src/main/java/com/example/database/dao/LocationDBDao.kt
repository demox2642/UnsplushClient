package com.example.database.dao

import androidx.room.*
import com.example.database.contracts.LocationDBContracts
import com.example.database.models.LocationDB

@Dao
interface LocationDBDao {
    @Query("SELECT * FROM ${LocationDBContracts.TABLE_NAME}")
    suspend fun getAllEmployers(): List<LocationDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployers(employers: List<LocationDB>)

    @Delete
    suspend fun deleteEmployers(employers: LocationDB)
}
