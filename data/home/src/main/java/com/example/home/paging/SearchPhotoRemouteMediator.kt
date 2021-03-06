package com.example.home.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.database.UnsplashDatabase
import com.example.database.models.* // ktlint-disable no-wildcard-imports
import com.example.home.services.HomeService

@ExperimentalPagingApi
class SearchPhotoRemouteMediator(
    private val homeService: HomeService,
    private val unsplashDatabase: UnsplashDatabase,
    private val searchText: String
) : RemoteMediator<Int, UnsplashImage>() {

    private val unsplashImageDao = unsplashDatabase.unsplashImageDao()
    private val unsplashSearchImageKeysDao = unsplashDatabase.unsplashSearchImageKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UnsplashImage>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            var lang: String? = null
            val ruLang = Regex("[А-я]+")
            if (ruLang.matches(searchText)) {
                lang = "ru"
            }
            val response = homeService.searchImages(query = searchText, page = currentPage, perPage = PagingConst.PAGE_SIZE, lang = lang)
            val endOfPaginationReached = response.images.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            unsplashDatabase.withTransaction {
//                if (loadType == LoadType.REFRESH) {
//                    unsplashImageDao.deleteAllImages()
//                    unsplashRemoteKeysDao.deleteAllRemoteKeys()
//                }
                val keys = response.images.map { unsplashImage ->
                    UnsplashSearchImageKeys(
                        id = unsplashImage.id!!,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                Log.e("SearchPhotoRemoute", "lang = $lang")
                // Log.e("SearchPhotoRemoute", "response = $response")
                unsplashSearchImageKeysDao.addAllRemoteKeys(remoteKeys = keys)
                try {

                    unsplashImageDao.addImages(
                        images = response.images.map {
                            Log.e("SearchPhotoRemoute", "response = ${it.description}")
                            UnsplashImage(
                                id = it.id!!,
                                urls = UrlsDB(regular = it.urls!!.regular!!),
                                likes = it.likes ?: 0,
                                description = if (refactorDescription(it.description) != null && refactorDescription(it.description) == false) {
                                    it.description
                                } else {
                                    null
                                },

                                user = UserDB(
                                    userLinks = UserLinks(html = it.user?.portfolioUrl ?: ""),
                                    username = it.user?.username ?: "user",
                                    profileImage = ProfileImageDB(
                                        id_prof_im = it.user?.id!!,
                                        small_im = it.user!!.profileImage?.small,
                                        medium = it.user!!.profileImage?.medium,
                                        large = it.user!!.profileImage?.large,
                                    )
                                ),
                                likedByUser = it.likedByUser!!
                            )
                        }
                    )
                } catch (e: Exception) {
                    Log.e("SearchPhotoRemoute", "ERROR insert key")
                }
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            Log.e("SearchPhotoRemoute", "Error $e")
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, UnsplashImage>
    ): UnsplashSearchImageKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                unsplashSearchImageKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UnsplashImage>
    ): UnsplashSearchImageKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                unsplashSearchImageKeysDao.getRemoteKeys(id = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, UnsplashImage>
    ): UnsplashSearchImageKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                unsplashSearchImageKeysDao.getRemoteKeys(id = unsplashImage.id)
            }
    }

    private fun refactorDescription(desc: String?): Boolean? {
        val ruLang = Regex("[А-я]+")
        return if (desc != null) {
            ruLang.matches(desc)
        } else {
            null
        }
    }
}
