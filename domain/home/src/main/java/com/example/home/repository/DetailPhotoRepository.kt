package com.example.home.repository

import com.example.home.models.DetailPhoto

interface DetailPhotoRepository {
    suspend fun getPhotoInfo(photoId: String): DetailPhoto
    suspend fun downloadPhoto(photoId: String)
}
