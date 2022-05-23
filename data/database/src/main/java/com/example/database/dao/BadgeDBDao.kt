package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.database.contracts.BadgeDBContracts
import com.example.database.models.BadgeDB

@Dao
interface BadgeDBDao {

    @Query("SELECT * FROM ${BadgeDBContracts.TABLE_NAME}")
    suspend fun getAllEmployers(): List<BadgeDB>

    @Insert
    suspend fun insertEmployers(employers: List<BadgeDB>)

    @Delete
    suspend fun deleteEmployers(employers: BadgeDB)
}
