package com.example.home.reposutory

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.database.UnsplashDatabase
import com.example.home.models.Order
import com.example.home.models.UnsplashImage
import com.example.home.paging.PagingConst.PAGE_SIZE
import com.example.home.paging.PhotoMediator
import com.example.home.repository.HomeRepository
import com.example.home.services.HomeService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class HomeRepositoryImp @Inject constructor(
    private val homeService: HomeService,
    private val unsplashDatabase: UnsplashDatabase
) : HomeRepository {
    //    override suspend fun getPhotosList(
//        page: Int?,
//        perPage: Int?,
//        order: Order?
//    ): Flow<PagingData<Photo>> {
//        return Pager(
//            config = PagingConfig(pageSize = PAGE_SIZE),
//            pagingSourceFactory = {
//                SearchPagingSource(homeService = homeService, order = order, unsplashDatabase)
//            }
//        ).flow
//    }
    override suspend fun getPhotosList(
        page: Int?,
        perPage: Int?,
        order: Order?,
    ): Flow<PagingData<UnsplashImage>> {
        val pagingSourceFactory = { unsplashDatabase.photoDBDao().getAllPhotoDBDao()}

        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            remoteMediator = PhotoMediator(
                homeService = homeService,
                order = order,
                unsplashDatabase = unsplashDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}
