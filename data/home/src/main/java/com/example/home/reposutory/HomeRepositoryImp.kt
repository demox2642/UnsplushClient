package com.example.home.reposutory

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.database.UnsplashDatabase
import com.example.home.models.HomePhoto
import com.example.home.paging.PagingConst.PAGE_SIZE
import com.example.home.paging.SearchPagingSource
import com.example.home.repository.HomeRepository
import com.example.home.services.HomeService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    private val homeService: HomeService,
    private val unsplashDatabase: UnsplashDatabase
) : HomeRepository {
    override suspend fun getPhotosList(): Flow<PagingData<HomePhoto>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                SearchPagingSource(homeService = homeService, unsplashDatabase = unsplashDatabase)
            }
        ).flow
    }
}
