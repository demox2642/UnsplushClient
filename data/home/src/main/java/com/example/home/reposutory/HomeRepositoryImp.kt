package com.example.home.reposutory

import android.util.Log
import androidx.paging.* // ktlint-disable no-wildcard-imports
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
                it.map {
                    Log.e("HomeRepositoryImp", "it = $it")
                }

                UnsplashPhotoToHomePhoto(it)
            }
    }

    override suspend fun searchPhotos(searchText: String): Flow<PagingData<HomePhoto>> {
        val pagingSourceFactory = {
            unsplashDatabase.unsplashImageDao().searchImages(searchText)
        }
        val test = Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            remoteMediator = SearchPhotoRemouteMediator(
                homeService = homeService,
                unsplashDatabase = unsplashDatabase,
                searchText = searchText
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
            .map {
                UnsplashPhotoToHomePhoto(it)
            }
        test.map {
            it.map {
                Log.e("HomeRepositoryImp", "it = $it")
            }
        }

        return test
    }

//    override suspend fun getPhotoInfo(photoId: String): HomePhoto {
//        val response = homeService.getPhotoInfo(photoId)
//        return HomePhoto(
//            id = response.id!!,
//            likes = response.likes,
//            urls_regular = response.urlsDomain?.regular,
//            user_name = response.userDomain?.username,
//            user_fio = response.userDomain?.username,
//            user_img = (response.userDomain?.portfolioUrl).toString(),
//            likedByUser = false
//        )
//    }

    private fun UnsplashPhotoToHomePhoto(insplashPhoto: PagingData<UnsplashImage>): PagingData<HomePhoto> {
        return insplashPhoto.map {
            HomePhoto(
                id = it.id,
                likes = it.likes,
                urls_regular = it.urls.regular,
                user_name = it.user.username,
                user_fio = it.user.username,
                user_img = it.user.profileImage!!.large,
                likedByUser = false
            )
        }
    }
}
