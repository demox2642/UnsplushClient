package com.example.home.usecase

import androidx.paging.PagingData
import com.example.home.models.HomePhoto
import com.example.home.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetPhotosListUserCase(private val homeRepository: HomeRepository) {

    suspend fun getPhotosList(): Flow<PagingData<HomePhoto>> = homeRepository.getPhotosList()
}
