package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.database.contracts.UrlsDBContracts
import com.example.database.models.UrlsDB

@Dao
interface UrlsDBDao {
    @Query("SELECT * FROM ${UrlsDBContracts.TABLE_NAME}")
    suspend fun getAllEmployers(): List<UrlsDB>

    @Insert
    suspend fun insertEmployers(employers: List<UrlsDB>)

    @Delete
    suspend fun deleteEmployers(employers: UrlsDB)
}
