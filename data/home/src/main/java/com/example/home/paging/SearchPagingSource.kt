package com.example.home.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.withTransaction
import com.example.database.UnsplashDatabase
import com.example.database.models.*
import com.example.home.models.HomePhoto
import com.example.home.models.Order
import com.example.home.paging.PagingConst.PAGE_SIZE
import com.example.home.services.HomeService

class SearchPagingSource(
    private val homeService: HomeService,
    private val unsplashDatabase: UnsplashDatabase
) : PagingSource<Int, HomePhoto>() {

    private val photoDBDao = unsplashDatabase.photoDBDao()
    private val profileImageDBDao = unsplashDatabase.profileImageDBDao()
    private val urlsDBDao = unsplashDatabase.urlsDBDao()
    private val userDBDao = unsplashDatabase.userDBDao()

    override fun getRefreshKey(state: PagingState<Int, HomePhoto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomePhoto> {
        val currentPage = params.key ?: 1
        return try {
            val response = homeService.getPhotoList(page = currentPage, perPage = PAGE_SIZE, orderBy = Order.POPULAR.name)

            val endOfPaginationReached = response.isEmpty()
            if (response.isNotEmpty()) {
                response.map { photo ->
                    // val photo = response[i]
                    Log.e("SearchPagingSource", "photo=${photo.location}")

                    var profileIm: ProfileImageDB? = null
                    var urls: UrlsDB? = null
                    var user: UserDB? = null

                    unsplashDatabase.withTransaction {
                        if (photo.user != null) {

                            if (photo.user!!.profileImage != null) {
                                profileIm = ProfileImageDB(
                                    id = photo.user!!.id!!,
                                    small = photo.user!!.profileImage!!.small,
                                    medium = photo.user!!.profileImage!!.medium,
                                    large = photo.user!!.profileImage!!.large
                                )
                                profileImageDBDao.insertProfileImageDB(profileIm!!)
                            }

                            user = UserDB(
                                id = photo.user!!.id!!,
                                username = photo.user!!.username,
                                name = photo.user!!.name,
                                bio = photo.user!!.bio,
                                location = photo.user!!.location,
                                totalLikes = photo.user!!.totalLikes,
                                downloads = photo.user!!.downloads,
                                profileImage = if (profileIm != null) {
                                    photo.user!!.id!!
                                } else {
                                    null
                                },
                                totalPhotos = photo.user!!.totalPhotos,
                                totalCollections = photo.user!!.totalCollections,
                                followedByUser = photo.user!!.followedByUser,
                                followersCount = photo.user!!.followersCount,
                                firstName = photo.user!!.firstName,
                                lastName = photo.user!!.lastName,
                                instagramUsername = photo.user!!.instagramUsername,
                                twitterUsername = photo.user!!.twitterUsername,
                                portfolioUrl = photo.user!!.portfolioUrl,
                                updatedAt = photo.user!!.updatedAt
                            )
                            userDBDao.insertUserDB(user!!)
                        }

                        if (photo.urls != null) {
                            urls = UrlsDB(
                                id = photo.id!!,
                                raw = photo.urls!!.raw,
                                full = photo.urls!!.full,
                                regular = photo.urls!!.regular,
                                small = photo.urls!!.small,
                                thumb = photo.urls!!.thumb,
                            )
                            urlsDBDao.insertUrlsDB(urls!!)
                        }

                        photoDBDao.insertPhotoDB(

                            PhotoDB(
                                id = photo.id!!,
                                width = photo.width,
                                height = photo.height,
                                color = photo.color,
                                downloads = photo.downloads,
                                likes = photo.likes,
                                urls = photo.id!!,
                                user = photo.user!!.id,
                                likedByUser = photo.likedByUser,
                                createdAt = photo.id,
                                updatedAt = photo.id,
                            )
                        )
                    }
                }

                val photoToHome = mutableListOf<HomePhoto>()
                response.forEach { Photo ->
                    photoToHome.add(
                        HomePhoto(
                            id = Photo.id!!,
                            likes = Photo.likes,
                            urls_regular = Photo.urls?.regular,
                            user_name = Photo.user?.username,
                            user_fio = Photo.user?.name,
                            user_img = Photo.user?.profileImage?.large,
                            likedByUser = Photo.likedByUser
                        )
                    )
                }

                LoadResult.Page(
                    data = photoToHome,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                var photoInDB: List<PhotoWithInfoDB> = emptyList()
                unsplashDatabase.withTransaction {
                    photoInDB = photoDBDao.getHomePhoto()
                }
                Log.e("SearchPagingSource", "NO INTERNET else")
                val photoToHome = mutableListOf<HomePhoto>()
                photoInDB.forEach { PhotoDB ->
                    photoToHome.add(
                        HomePhoto(
                            id = PhotoDB.id,
                            likes = PhotoDB.likes,
                            urls_regular = PhotoDB.urls_regular,
                            user_name = PhotoDB.user_username,
                            user_fio = PhotoDB.user_name,
                            user_img = PhotoDB.user_profileImage_large,
                            likedByUser = PhotoDB.likedByUser
                        )
                    )
                }
                Log.e("SearchPagingSource", "NO INTERNET photo=$photoToHome")
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            var photoInDB: List<PhotoWithInfoDB> = emptyList()
            unsplashDatabase.withTransaction {
                photoInDB = photoDBDao.getHomePhoto()
            }
            Log.e("SearchPagingSource", "NO INTERNET else")
            val photoToHome = mutableListOf<HomePhoto>()
            photoInDB.map { PhotoDB ->
                photoToHome.add(
                    HomePhoto(
                        id = PhotoDB.id,
                        likes = PhotoDB.likes,
                        urls_regular = PhotoDB.urls_regular,
                        user_name = PhotoDB.user_username,
                        user_fio = PhotoDB.user_name,
                        user_img = PhotoDB.user_profileImage_large,
                        likedByUser = PhotoDB.likedByUser
                    )
                )
            }
            Log.e("SearchPagingSource", "NO INTERNET photo=$photoToHome")
            Log.e("SearchPagingSource", "NO INTERNET $e")

            if (photoToHome.isEmpty()) {
                LoadResult.Error(Throwable("No Internet, no cache"))
            } else {
                LoadResult.Page(
                    data = photoToHome,
                    prevKey = null,
                    nextKey = null
                )
            }
        }
    }
}
