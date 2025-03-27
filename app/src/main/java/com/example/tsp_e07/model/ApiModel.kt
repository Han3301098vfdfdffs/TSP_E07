package com.example.tsp_e07.model

import android.icu.text.ListFormatter.Width
import kotlinx.serialization.Serializable

@Serializable
data class ApiPhoto(
    val id:String,
    val url:String,
    val width: Int,
    val height: Int,
)
