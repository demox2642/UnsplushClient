package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.home.contracts.PhotoRemoteKeysDBContracts
import com.example.home.models.UnsplashRemoteKeys

@Dao
interface PhotoRemoteKeysDBDao {

    @Query("SELECT * FROM ${PhotoRemoteKeysDBContracts.TABLE_NAME} WHERE ${PhotoRemoteKeysDBContracts.Colums.ID} =:id")
    suspend fun getRemoteKeys(id: String): UnsplashRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<UnsplashRemoteKeys>)

    @Query("DELETE FROM ${PhotoRemoteKeysDBContracts.TABLE_NAME} ")
    suspend fun deleteAllRemoteKeys()

}