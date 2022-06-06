package com.example.home.usecase

import com.example.home.models.DetailPhoto
import com.example.home.repository.DetailPhotoRepository

class GetPhotoInfoUserCase(private val detailPhotoRepository: DetailPhotoRepository) {
    suspend fun getPhotoInfo(photoId: String): DetailPhoto = detailPhotoRepository.getPhotoInfo(photoId)
}
