package com.example.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.contracts.UnsplashImageDBContracts
import com.example.database.models.UnsplashImage

@Dao
interface UnsplashImageDao {

    @Query("SELECT * FROM ${UnsplashImageDBContracts.TABLE_NAME}")
    fun getAllImages(): PagingSource<Int, UnsplashImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImages(images: List<UnsplashImage>)

    @Query("DELETE FROM ${UnsplashImageDBContracts.TABLE_NAME}")
    suspend fun deleteAllImages()
}
