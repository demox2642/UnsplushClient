package com.example.home.reposutory

import com.example.database.UnsplashDatabase
import com.example.database.models.* // ktlint-disable no-wildcard-imports
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
                        location = if (response.location?.city != null || response.location?.country != null) {
                            LocationDomain(
                                city = response.location?.city,
                                country = response.location?.country,
                                positionDomain = if (response.location?.position?.latitude != null && response.location?.position?.longitude != null) {
                                    PositionDomain(
                                        latitude = response.location?.position?.latitude!!,
                                        longitude = response.location?.position?.longitude!!
                                    )
                                } else { null },

                            )
                        } else { null },
                        userDomain = UserDomain(
                            username = response?.user?.username,
                            name = response?.user?.name,
                            profileImageDomain = ProfileImageDomain(
                                small = response?.user?.profileImage?.small
                            )
                        ),
                        categories = response?.categories.map {
                            it.title
                        }.joinToString("#"),
                        description = response.description,
                        downloadLink = response.urls!!.raw,
                        likedByUser = response.likedByUser,
                        exifDomain = ExifDomain(
                            make = response.exif?.make,
                            model = response.exif?.model,
                            aperture = response.exif?.aperture,
                            iso = response.exif?.iso,
                            exposureTime = response.exif?.exposureTime,
                            focalLength = response.exif?.focalLength,
                        )

                    )
                    unsplashDatabase.unsplashImageDao().insertImage(
                        UnsplashImage(
                            id = detailPhoto.id!!,
                            description = detailPhoto.description,
                            urls = UrlsDB(
                                raw = response.urls!!.raw,

                                full = response.urls!!.full,

                                regular = response.urls!!.regular!!,

                                small = response.urls!!.small,

                                thumb = response.urls!!.thumb,
                            ),
                            likes = detailPhoto.likes!!,
                            user = UserDB(
                                username = response.user.id,
                                name = response.user.id,
                                bio = response.user.id,
                                location_user = response.user.location,
                                totalLikes = response.user.totalLikes,
                                downloads_user = response.user.downloads,
                                profileImage = ProfileImageDB(
                                    id_prof_im = response.user.id!!,

                                    small_im = response.user.profileImage?.small,

                                    medium = response.user.profileImage?.medium,

                                    large = response.user.profileImage?.large,
                                ),
                                totalPhotos = response.user.totalPhotos,
                                totalCollections = response.user.totalCollections,
                                followedByUser = response.user.followedByUser,
                                followersCount = response.user.followersCount,
                                firstName = response.user.firstName,
                                lastName = response.user.lastName,
                                instagramUsername = response.user.instagramUsername,
                                twitterUsername = response.user.twitterUsername,
                                portfolioUrl = response.user.portfolioUrl,
                                updatedAt_user = response.user.updatedAt,
                                userLinks = UserLinks(response.user.links?.html.toString())
                            ),
                            likedByUser = response.likedByUser!!,
                            width = response.width,
                            height = response.height,
                            color = response.color,
                            downloads = response.downloads,

                            location = LocationDB(
                                city = response.location?.city,
                                country = response.location?.country,
                                position = PositionDB(
                                    latitude = response.location?.position?.latitude,
                                    longitude = response.location?.position?.longitude,
                                )
                            ),

                            categories = response.categories.map {
                                it.title
                            }.joinToString("#"),

                            exif = ExifDB(),
                            createdAt = response.createdAt,
                            updatedAt = response.updatedAt
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
            width = response.width,
            height = response.width,
            color = response.color,
            downloads = response.downloads,
            likes = response.likes,
            urlsPhoto = response.urls.regular,
            location = LocationDomain(
                city = response.location?.city,
                country = response.location?.country,
                positionDomain = if (response.location?.position?.latitude != null && response.location?.position?.longitude != null) {
                    PositionDomain(
                        latitude = response.location?.position?.latitude!!,
                        longitude = response.location?.position?.longitude!!
                    )
                } else { null }

            ),
            userDomain = UserDomain(
                username = response.user.username,
                name = response.user.name,
                profileImageDomain = ProfileImageDomain(
                    small = response.user.profileImage?.small_im
                )
            ),
            categories = response.categories,
            description = response.description,
            downloadLink = response.urls.raw,
            likedByUser = response.likedByUser,
            exifDomain = ExifDomain(
                make = response.exif?.make,
                model = response.exif?.model,
                aperture = response.exif?.aperture,
                iso = response.exif?.iso,
                exposureTime = response.exif?.exposureTime,
                focalLength = response.exif?.focalLength,
            ),
//            likes = response.likes,
//            urlsPhoto = response.urls.regular,
//            userDomain = UserDomain(
//                username = response.user.username,
//                profileImageDomain = ProfileImageDomain(
//                    small = response.user.profileImage?.small_im
//                )
//            ),
//            likedByUser = response.likedByUser,
            code = code,
            errorMessage = errorMessage
        )
    }
}
