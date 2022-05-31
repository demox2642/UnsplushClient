package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.contracts.UnsplushRemoteKeyContracts
import com.example.database.models.UnsplashRemoteKeys

@Dao
interface UnsplashRemoteKeysDao {

    @Query("SELECT * FROM ${UnsplushRemoteKeyContracts.TABLE_NAME} WHERE id =:id")
    suspend fun getRemoteKeys(id: String): UnsplashRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<UnsplashRemoteKeys>)

    @Query("DELETE FROM ${UnsplushRemoteKeyContracts.TABLE_NAME}")
    suspend fun deleteAllRemoteKeys()
}
