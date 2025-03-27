package com.example.tsp_e07.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tsp_e07.network.Api
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.IOException

class ApiViewModel:ViewModel(){
    var apiUiState by mutableStateOf("")
        private set

    init {
        getApiPhotos()
    }

    fun getApiPhotos(){
        viewModelScope.launch {
            try{
                val listResult = Api.retrofitService.getPhotos()
                apiUiState = listResult
            }catch (e: IOException){

            }
        }
    }
}