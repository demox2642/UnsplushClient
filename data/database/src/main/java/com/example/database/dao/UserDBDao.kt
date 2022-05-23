package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.database.contracts.UserDBContracts
import com.example.database.models.UserDB

@Dao
interface UserDBDao {
    @Query("SELECT * FROM ${UserDBContracts.TABLE_NAME}")
    suspend fun getAllEmployers(): List<UserDB>

    @Insert
    suspend fun insertEmployers(employers: List<UserDB>)

    @Delete
    suspend fun deleteEmployers(employers: UserDB)
}
