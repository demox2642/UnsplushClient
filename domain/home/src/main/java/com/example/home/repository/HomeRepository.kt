package com.example.home.repository

import androidx.paging.PagingData
import com.example.home.models.Order
import com.example.home.models.UnsplashImage
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getPhotosList(
        page: Int?,
        perPage: Int?,
        order: Order?
    ): Flow<PagingData<UnsplashImage>>
}
