package com.example.home.usecase

import androidx.paging.PagingData
import com.example.home.models.HomePhoto
import com.example.home.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class SearchPhotoUserCase(private val homeRepository: HomeRepository) {

    suspend fun searchPhotos(searchText: String): Flow<PagingData<HomePhoto>> = homeRepository.searchPhotos(searchText)
}
