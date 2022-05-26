package com.example.home.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.database.UnsplashDatabase
import com.example.home.models.*
import com.example.home.services.HomeService

@ExperimentalPagingApi
class PhotoMediator(
    private val homeService: HomeService,
    private val order: Order?,
    private val unsplashDatabase: UnsplashDatabase
) : RemoteMediator<Int, UnsplashImage>() {

    //   private val profileImageDBDao = unsplashDatabase.profileImageDBDao()
//    private val userDBDao = unsplashDatabase.userDBDao()
//    private val urlsDBDao = unsplashDatabase.urlsDBDao()
    private val photoDBDao = unsplashDatabase.photoDBDao()
    private val photoRemoteKeysDBDao = unsplashDatabase.photoRemoteKeysDBDao()

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

            val response = homeService.getPhotoList(page = currentPage, perPage = PagingConst.PAGE_SIZE, orderBy = order?.name)
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            unsplashDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    photoDBDao.deleteAllPhotoDBDao()
                    photoRemoteKeysDBDao.deleteAllRemoteKeys()
                }
                val keys = response.map { unsplashImage ->
                    UnsplashRemoteKeys(
                        id = unsplashImage.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                photoRemoteKeysDBDao.addAllRemoteKeys(remoteKeys = keys)
                photoDBDao.insertPhotoDBDao(images = response)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, UnsplashImage>
    ): UnsplashRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                photoRemoteKeysDBDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UnsplashImage>
    ): UnsplashRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                photoRemoteKeysDBDao.getRemoteKeys(id = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, UnsplashImage>
    ): UnsplashRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                photoRemoteKeysDBDao.getRemoteKeys(id = unsplashImage.id)
            }
    }
}
