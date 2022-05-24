package com.example.database.dao

import androidx.room.*
import com.example.database.contracts.UrlsDBContracts
import com.example.database.models.UrlsDB

@Dao
interface UrlsDBDao {
    @Query("SELECT * FROM ${UrlsDBContracts.TABLE_NAME}")
    suspend fun getAllEmployers(): List<UrlsDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployers(employers: List<UrlsDB>)

    @Delete
    suspend fun deleteEmployers(employers: UrlsDB)
}
