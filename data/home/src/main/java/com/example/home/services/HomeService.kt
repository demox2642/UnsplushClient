package com.example.home.services

import com.example.home.models.Photo
import com.example.home.models.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    @GET("photos")
    suspend fun getPhotoList(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("order_by") orderBy: String?
    ): List<Photo>

    @GET("search/photos")
    suspend fun searchImages(
        @Query("query") query: String,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int,
        @Query("lang") lang: String?
    ): SearchResult //List<Photo>

    @GET("photos/:id")
    suspend fun getPhotoInfo(
        @Query("id") id: String,
    ):Photo
}
