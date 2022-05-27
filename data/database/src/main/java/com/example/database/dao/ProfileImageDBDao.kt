package com.example.database.dao

import androidx.room.*
import com.example.database.contracts.ProfileImageDBContracts
import com.example.database.models.ProfileImageDB

@Dao
interface ProfileImageDBDao {
    @Query("SELECT * FROM ${ProfileImageDBContracts.TABLE_NAME}")
    suspend fun getAllProfileImageDB(): List<ProfileImageDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfileImageDB(profileImageDB: ProfileImageDB)

    @Delete
    suspend fun deleteProfileImageDB(profileImageDB: ProfileImageDB)
}
