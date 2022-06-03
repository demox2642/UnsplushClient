package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.contracts.UnsplushRemoteKeyContracts
import com.example.database.models.UnsplashSearchImageKeys

@Dao
interface UnsplashSearchImageKeysDao {

    @Query("SELECT * FROM ${UnsplushRemoteKeyContracts.SEARCH_TABLE_NAME} WHERE id =:id")
    suspend fun getRemoteKeys(id: String): UnsplashSearchImageKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<UnsplashSearchImageKeys>)

    @Query("DELETE FROM ${UnsplushRemoteKeyContracts.SEARCH_TABLE_NAME}")
    suspend fun deleteAllRemoteKeys()
}
