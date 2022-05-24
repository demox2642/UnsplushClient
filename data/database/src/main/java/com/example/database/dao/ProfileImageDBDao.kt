package com.example.database.dao

import androidx.room.*
import com.example.database.contracts.ProfileImageDBContracts
import com.example.database.models.ProfileImageDB

@Dao
interface ProfileImageDBDao {
    @Query("SELECT * FROM ${ProfileImageDBContracts.TABLE_NAME}")
    suspend fun getAllEmployers(): List<ProfileImageDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployers(employers: List<ProfileImageDB>)

    @Delete
    suspend fun deleteEmployers(employers: ProfileImageDB)
}
