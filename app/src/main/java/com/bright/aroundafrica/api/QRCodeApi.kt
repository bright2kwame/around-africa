package com.bright.aroundafrica.api

import retrofit2.http.GET
import retrofit2.http.Path

interface QRCodeApi {

    @GET("v1/create-qr-code/?size=150x150&data={payload}")
    suspend fun generateQrCode(@Path(value = "payload") payload: String): String

}