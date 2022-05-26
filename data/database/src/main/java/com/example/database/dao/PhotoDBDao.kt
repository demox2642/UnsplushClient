package com.example.database.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.home.contracts.PhotoDBContracts
import com.example.home.models.UnsplashImage

@Dao
interface PhotoDBDao {
    @Query("SELECT * FROM ${PhotoDBContracts.TABLE_NAME}")
    fun getAllPhotoDBDao(): PagingSource<Int, UnsplashImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotoDBDao(images: List<UnsplashImage>)

//    @Delete
//    suspend fun deletePhotoDBDao(photoDB: Photo)

    @Query("DELETE FROM ${PhotoDBContracts.TABLE_NAME}")
    suspend fun deleteAllPhotoDBDao()
}
