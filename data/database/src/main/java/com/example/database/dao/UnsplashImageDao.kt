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

    @Query("SELECT * FROM ${UnsplashImageDBContracts.TABLE_NAME} WHERE description LIKE  '%' ||  :desc|| '%'")
    fun searchImages(desc: String): PagingSource<Int, UnsplashImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImages(images: List<UnsplashImage>)

    @Query("SELECT * FROM ${UnsplashImageDBContracts.TABLE_NAME} WHERE ${UnsplashImageDBContracts.Colums.ID} = :id")
    suspend fun getImageWithInfo(id: String): UnsplashImage

    @Query("DELETE FROM ${UnsplashImageDBContracts.TABLE_NAME}")
    suspend fun deleteAllImages()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(unsplashImage: UnsplashImage)
}
