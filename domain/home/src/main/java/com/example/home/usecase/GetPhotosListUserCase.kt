package com.example.home.usecase

import androidx.paging.PagingData
import com.example.home.models.Order
import com.example.home.models.Photo
import com.example.home.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetPhotosListUserCase(private val homeRepository: HomeRepository) {

    suspend fun getPhotosList(
        page: Int?,
        perPage: Int?,
        order: Order?
    ): Flow<PagingData<Photo>> = homeRepository.getPhotosList(page, perPage, order)
}
