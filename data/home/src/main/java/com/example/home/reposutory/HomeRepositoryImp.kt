package com.example.home.reposutory

import androidx.paging.*
import com.example.database.UnsplashDatabase
import com.example.database.models.UnsplashImage
import com.example.home.models.HomePhoto
import com.example.home.paging.HomePhotoRemouteMediator
import com.example.home.paging.PagingConst.PAGE_SIZE
import com.example.home.paging.SearchPhotoRemouteMediator
import com.example.home.repository.HomeRepository
import com.example.home.services.HomeService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
@ExperimentalPagingApi
class HomeRepositoryImp @Inject constructor(
    private val homeService: HomeService,
    private val unsplashDatabase: UnsplashDatabase
) : HomeRepository {
//    override suspend fun getPhotosList(): Flow<PagingData<HomePhoto>> {
//        return Pager(
//            config = PagingConfig(pageSize = PAGE_SIZE),
//            pagingSourceFactory = {
//                SearchPagingSource(homeService = homeService, unsplashDatabase = unsplashDatabase)
//            }
//        ).flow
//    }

    override suspend fun getPhotosList(): Flow<PagingData<HomePhoto>> {
        val pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            remoteMediator = HomePhotoRemouteMediator(
                homeService = homeService,
                unsplashDatabase = unsplashDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map {
                UnsplashPtotoToHomePhoto(it)
            }
    }

    override suspend fun searchPhotos(searchText: String): Flow<PagingData<HomePhoto>> {
        val pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            remoteMediator = SearchPhotoRemouteMediator(
                homeService = homeService,
                unsplashDatabase = unsplashDatabase,
                searchText = searchText
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map {
                UnsplashPtotoToHomePhoto(it)
            }
    }

    override suspend fun getPhotoInfo(photoId: String): HomePhoto {
        val response = homeService.getPhotoInfo(photoId)
        return  HomePhoto(
            id = response.id!!,
            likes = response.likes,
            urls_regular = response.urls?.regular,
            user_name = response.user?.username,
            user_fio = response.user?.username,
            user_img = (response.user?.portfolioUrl).toString(),
            likedByUser = false
        )
    }

    private fun UnsplashPtotoToHomePhoto(insplashPhoto: PagingData<UnsplashImage>): PagingData<HomePhoto> {
        return insplashPhoto.map {
            HomePhoto(
                id = it.id,
                likes = it.likes,
                urls_regular = it.urls.regular,
                user_name = it.user.username,
                user_fio = it.user.username,
                user_img = (it.user.userLinks).toString(),
                likedByUser = false
            )
        }
    }
}
