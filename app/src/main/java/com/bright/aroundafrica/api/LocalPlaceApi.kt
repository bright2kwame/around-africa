package com.bright.aroundafrica.api

import com.bright.aroundafrica.model.LocalPlace
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface LocalPlaceApi {

    @Headers("Content-Type:application/json")
    @GET("photos/{id}")
    suspend fun getPlace(@Path(value = "id") id: String): LocalPlace?

    @GET("photos")
    suspend fun getPlaces(): List<LocalPlace>

}