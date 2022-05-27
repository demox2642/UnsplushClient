package com.example.database.dao

import androidx.room.*
import com.example.database.contracts.UserDBContracts
import com.example.database.models.UserDB

@Dao
interface UserDBDao {
    @Query("SELECT * FROM ${UserDBContracts.TABLE_NAME}")
    suspend fun getAllUserDB(): List<UserDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDB(userDB: UserDB)

    @Delete
    suspend fun deleteUserDB(userDB: UserDB)
}
