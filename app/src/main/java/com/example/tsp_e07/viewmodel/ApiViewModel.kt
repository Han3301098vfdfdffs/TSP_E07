package com.example.tsp_e07.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tsp_e07.model.ApiPhoto
import com.example.tsp_e07.network.Api
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface ApiUiState{

    data class Success(val photo:ApiPhoto) : ApiUiState

    object Error: ApiUiState

    object Loading: ApiUiState
}
class ApiViewModel:ViewModel(){
    var apiUiState:ApiUiState by mutableStateOf(ApiUiState.Loading)
        private set

    init {
        getApiPhotos()
    }

    fun getApiPhotos(){
        viewModelScope.launch {
            apiUiState = try {
                val listResult = Api.retrofitService.getPhotos()
                ApiUiState.Success(listResult[0])
            }catch (e: IOException){
                ApiUiState.Error
            }
        }
    }
}