package com.example.database.dao

import androidx.room.*
import com.example.database.contracts.UrlsDBContracts
import com.example.database.models.UrlsDB

@Dao
interface UrlsDBDao {
    @Query("SELECT * FROM ${UrlsDBContracts.TABLE_NAME}")
    suspend fun getAllUrlsDB(): List<UrlsDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUrlsDB(urlsDB: UrlsDB)

    @Delete
    suspend fun deleteUrlsDB(urlsDB: UrlsDB)
}
