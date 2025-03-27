package com.example.tsp_e07.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val API_URL = "https://api.thecatapi.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(API_URL)
    .build()

interface ApiService{
    @GET("v1/images/search")
    suspend fun getPhotos():String
}

object Api{
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}