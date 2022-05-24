package com.example.database.dao

import androidx.room.*
import com.example.database.contracts.UserDBContracts
import com.example.database.models.UserDB

@Dao
interface UserDBDao {
    @Query("SELECT * FROM ${UserDBContracts.TABLE_NAME}")
    suspend fun getAllEmployers(): List<UserDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployers(employers: List<UserDB>)

    @Delete
    suspend fun deleteEmployers(employers: UserDB)
}
