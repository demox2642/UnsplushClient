package com.example.home.services

import com.example.home.models.Photo
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailPhotoService {

    @GET("photos/{id}")
    suspend fun getPhotoInfo(
        @Path("id") id: String,
    ): Photo
}
