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

        var detailPhoto: DetailPhoto
        try {
            detailPhotoService.getPhotoInfo(photoId).apply {
                val response = this.body()
                if (this.isSuccessful) {
                    detailPhoto = DetailPhoto(
                        id = photoId,
                        width = response?.width,
                        height = response?.width,
                        color = response?.color,
                        downloads = response?.downloads,
                        likes = response?.likes,
                        urlsPhoto = response?.urls!!.regular,
                        location = LocationDomain(
                            city = response?.location?.city,
                            country = response?.location?.country,
                            positionDomain = PositionDomain(
                                latitude = response?.location?.position?.latitude,
                                longitude = response?.location?.position?.latitude
                            ),

                        ),
                        userDomain = UserDomain(
                            id = response?.user?.id,
                            username = response?.user?.username,
                            name = response?.user?.name,
                            profileImageDomain = ProfileImageDomain(
                                small = response?.user?.profileImage?.small
                            )
                        ),
                        categories = response?.categories.map {
                            CategoryDomain(
                                it.id,
                                it.title
                            )
                        },
                        description = response.description,
                        downloadLink = response.urls!!.raw,
                        likedByUser = response?.likedByUser,
                        exifDomain = ExifDomain(
                            make = response.exif?.make,
                            model = response.exif?.model,
                            aperture = response.exif?.aperture,
                            iso = response.exif?.iso,
                            exposureTime = response.exif?.exposureTime,
                            focalLength = response.exif?.focalLength,
                        )

                    )
                } else {
                    detailPhoto = getPhotoFromCashe(photoId = photoId, code = this.code(), errorMessage = this.message())
                }
            }
        } catch (e: Exception) {
            detailPhoto = getPhotoFromCashe(photoId = photoId, code = 1, errorMessage = e.message.toString())
        }

        return detailPhoto
    }

    private suspend fun getPhotoFromCashe(photoId: String, code: Int, errorMessage: String): DetailPhoto {
        val response = unsplashDatabase.unsplashImageDao().getImageWithInfo(id = photoId)
        return DetailPhoto(
            id = photoId,
            likes = response.likes,
            urlsPhoto = response.urls.regular,
            userDomain = UserDomain(
                username = response.user.username,
                profileImageDomain = ProfileImageDomain(
                    small = response.user.profileImage?.small
                )
            ),
            likedByUser = response.likedByUser,
            code = code,
            errorMessage = errorMessage
        )
    }
}
