package com.example.home.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.home.models.Order
import com.example.home.models.Photo
import com.example.home.paging.PagingConst.PAGE_SIZE
import com.example.home.services.HomeService

class SearchPagingSource(
    private val homeService: HomeService,
    private val order: Order?
) : PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val currentPage = params.key ?: 1
        return try {
            val response = homeService.getPhotoList(page = currentPage, perPage = PAGE_SIZE, orderBy = order?.name)
            val endOfPaginationReached = response.isEmpty()
            if (response.isNotEmpty()) {
                LoadResult.Page(
                    data = response,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
