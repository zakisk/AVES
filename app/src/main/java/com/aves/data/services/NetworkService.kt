package com.aves.data.services

import com.aves.domain.models.global.Photo
import com.aves.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("photos/")
    suspend fun getPhotos(@Query("client_id") id: String = Constants.CLIENT_ID): Response<List<Photo>>

    @GET("photos/{id}/")
    suspend fun getPhoto(
        @Path("id") id: String,
        @Query("client_id") clientId: String = Constants.CLIENT_ID
    ) : Response<Photo>

}