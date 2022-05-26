package com.example.home.services


import com.example.home.models.UnsplashImage
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    @GET("photos")
    suspend fun getPhotoList(
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("order_by") orderBy: String?
    ): List<UnsplashImage>
}
