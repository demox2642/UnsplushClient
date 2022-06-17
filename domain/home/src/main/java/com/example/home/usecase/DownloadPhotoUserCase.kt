package com.example.home.usecase

import com.example.home.repository.DetailPhotoRepository

class DownloadPhotoUserCase(private val detailPhotoRepository: DetailPhotoRepository) {
    suspend fun downloadPhoto(photoId: String) = detailPhotoRepository.downloadPhoto(photoId)
}
