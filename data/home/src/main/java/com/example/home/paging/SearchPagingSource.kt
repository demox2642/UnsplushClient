//package com.example.home.paging
//
//import android.util.Log
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import androidx.room.withTransaction
//import com.example.database.UnsplashDatabase
//import com.example.database.models.*
//import com.example.home.models.Order
//import com.example.home.models.Photo
//import com.example.home.paging.PagingConst.PAGE_SIZE
//import com.example.home.services.HomeService
//
//class SearchPagingSource(
//    private val homeService: HomeService,
//    private val order: Order?,
//    private val unsplashDatabase: UnsplashDatabase
//) : PagingSource<Int, Photo>() {
//    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
//        return state.anchorPosition
//    }
//
//    private val profileImageDBDao = unsplashDatabase.profileImageDBDao()
////    private val userDBDao = unsplashDatabase.userDBDao()
////    private val urlsDBDao = unsplashDatabase.urlsDBDao()
//    private val photoDBDao = unsplashDatabase.photoDBDao()
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
//        val currentPage = params.key ?: 1
//
//        return try {
//            val response = homeService.getPhotoList(page = currentPage, perPage = PAGE_SIZE, orderBy = order?.name)
//            val endOfPaginationReached = response.isEmpty()
//
//            if (response.isNotEmpty()) {
//                try {
//                    unsplashDatabase.withTransaction {
//
//                        for (i in 0 until response.size) {
//
//                            val profileIm =
//                                ProfileImageDB(
//                                    id = response[i].user?.id!!,
//                                    small = response[i].user?.profileImage!!.small,
//                                    medium = response[i].user?.profileImage!!.medium,
//                                    large = response[i].user?.profileImage!!.large
//                                )
//
//                            val user =
//                                UserDB(
//                                    user_id = response[i].user!!.id!!,
//                                    username = response[i].user!!.username,
//                                    name = response[i].user!!.name,
//                                    bio = response[i].user!!.bio,
//                                    location = response[i].user!!.location,
//                                    totalLikes = response[i].user!!.totalLikes,
//                                    downloads = response[i].user!!.downloads,
//                                    profileImage = profileIm,
//                                    totalPhotos = response[i].user!!.totalPhotos,
//                                    totalCollections = response[i].user!!.totalCollections,
//                                    followedByUser = response[i].user!!.followedByUser,
//                                    followersCount = response[i].user!!.followersCount,
//                                    firstName = response[i].user!!.firstName,
//                                    lastName = response[i].user!!.lastName,
//                                    instagramUsername = response[i].user!!.instagramUsername,
//                                    twitterUsername = response[i].user!!.twitterUsername,
//                                    portfolioUrl = response[i].user!!.portfolioUrl,
//                                    user_updatedAt = response[i].user!!.updatedAt
//                                )
//
//                            val urls =
//                                UrlsDB(
//                                    // id = response[i].id,
//                                    raw = response[i].urls!!.raw,
//                                    full = response[i].urls!!.full,
//                                    regular = response[i].urls!!.regular,
//                                    //   small = response[i].urls!!.small,
//                                    thumb = response[i].urls!!.thumb
//                                )
//
//                            photoDBDao.insertPhotoDBDao(
//                                PhotoDB(
//                                    photo_id = response[i].id,
//                                    width = response[i].width,
//                                    height = response[i].height,
//                                    likes = response[i].likes,
//                                    photo_urls = urls,
//                                    user = user,
//                                    likedByUser = response[i].likedByUser,
//                                    createdAt = response[i].createdAt,
//                                    updatedAt = response[i].updatedAt,
//                                )
//                            )
//                        }
//                    }
//                } catch (e: Exception) {
//                    Log.e("SearchPagingSource", "ERROR:$e")
//                }
//
//                LoadResult.Page(
//                    data = response,
//                    prevKey = if (currentPage == 1) null else currentPage - 1,
//                    nextKey = if (endOfPaginationReached) null else currentPage + 1
//                )
//            } else {
//                LoadResult.Page(
//                    data = emptyList(),
//                    prevKey = null,
//                    nextKey = null
//                )
//            }
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//}
