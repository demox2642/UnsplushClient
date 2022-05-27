package com.example.home.repository

import androidx.paging.PagingData
import com.example.home.models.HomePhoto
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getPhotosList(): Flow<PagingData<HomePhoto>>
}
