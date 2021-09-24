package com.bright.aroundafrica.api

import com.bright.aroundafrica.util.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object BaseNetwork {


    val retrofitLocalPlace: LocalPlaceApi by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.baseApi)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LocalPlaceApi::class.java)
    }

    val retrofitQRCodeApi: QRCodeApi by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstants.baseQRCodeApi)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QRCodeApi::class.java)
    }
}