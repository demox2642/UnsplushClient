package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.database.contracts.ProfileImageDBContracts
import com.example.database.models.ProfileImageDB

@Dao
interface ProfileImageDBDao {
    @Query("SELECT * FROM ${ProfileImageDBContracts.TABLE_NAME}")
    suspend fun getAllEmployers(): List<ProfileImageDB>

    @Insert
    suspend fun insertEmployers(employers: List<ProfileImageDB>)

    @Delete
    suspend fun deleteEmployers(employers: ProfileImageDB)
}
