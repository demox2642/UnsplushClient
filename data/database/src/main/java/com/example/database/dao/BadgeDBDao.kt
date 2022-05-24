package com.example.database.dao

import androidx.room.*
import com.example.database.contracts.BadgeDBContracts
import com.example.database.models.BadgeDB

@Dao
interface BadgeDBDao {

    @Query("SELECT * FROM ${BadgeDBContracts.TABLE_NAME}")
    suspend fun getAllEmployers(): List<BadgeDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployers(employers: List<BadgeDB>)

    @Delete
    suspend fun deleteEmployers(employers: BadgeDB)
}
