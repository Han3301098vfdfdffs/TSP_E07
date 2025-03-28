package com.example.tsp_e07.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiPhoto(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val download_url: String
)