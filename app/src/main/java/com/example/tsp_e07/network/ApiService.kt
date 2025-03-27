package com.example.tsp_e07.network
import com.example.tsp_e07.model.ApiPhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val API_URL = "https://picsum.photos/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(API_URL)
    .build()

interface ApiService{
    @GET("v2/list?page=2&limit=15")
    suspend fun getPhotos():List<ApiPhoto>
}

object Api{
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}