package com.example.database.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.database.contracts.UnsplashImageDBContracts
import com.example.database.models.UnsplashImage

@Dao
interface UnsplashImageDao {

    @Query("SELECT * FROM ${UnsplashImageDBContracts.TABLE_NAME}")
    fun getAllImages(): PagingSource<Int, UnsplashImage>

    @Query("SELECT * FROM ${UnsplashImageDBContracts.TABLE_NAME} WHERE description LIKE  '%' ||  :desc|| '%'")
    fun searchImages(desc: String): PagingSource<Int, UnsplashImage>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addImages(images: List<UnsplashImage>)

    @Query("SELECT * FROM ${UnsplashImageDBContracts.TABLE_NAME} WHERE ${UnsplashImageDBContracts.Colums.ID} = :id")
    suspend fun getImageWithInfo(id: String): UnsplashImage

    @Query("DELETE FROM ${UnsplashImageDBContracts.TABLE_NAME}")
    suspend fun deleteAllImages()

    @Update(entity = UnsplashImage::class)
    suspend fun insertImage(unsplashImage: UnsplashImage)
}
