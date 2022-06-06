package com.example.home.reposutory

import com.example.database.UnsplashDatabase
import com.example.home.models.* // ktlint-disable no-wildcard-imports
import com.example.home.repository.DetailPhotoRepository
import com.example.home.services.DetailPhotoService
import javax.inject.Inject

class DetailPhotoRepositoryImpl @Inject constructor(
    private val detailPhotoService: DetailPhotoService,
    private val unsplashDatabase: UnsplashDatabase
) : DetailPhotoRepository {

    override suspend fun getPhotoInfo(photoId: String): DetailPhoto {
        val photo = detailPhotoService.getPhotoInfo(photoId)
        return DetailPhoto(
            id = photo.id,
            width = photo.width,
            height = photo.height,
            color = photo.color,
            downloads = photo.downloads,
            likes = photo.likes,
            location = null,
            urlsDomain = null,
            linksDomain = null,
            userDomain = null,
            // categories = null,
            description = photo.description,
            likedByUser = photo.likedByUser,
            createdAt = photo.createdAt,
            updatedAt = photo.updatedAt,
//            location = if (photo.location != null) {
//                LocationDomain(
//                    city = photo.location?.city,
//                    country = photo.location?.country,
//                    positionDomain = PositionDomain(
//                        latitude = photo.location?.positionDomain?.latitude,
//                        longitude = photo.location?.positionDomain?.longitude
//                    )
//                )
//            } else { null },
//            urlsDomain = photo.urlsDomain,
//            linksDomain = photo.linksDomain,
//            userDomain = photo.userDomain,
//            categories = photo.categories,
//            description = photo.description,
//            likedByUser = photo.likedByUser,
//            createdAt = photo.createdAt,
//            updatedAt = photo.updatedAt,
        )
    }
}
