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

sealed interface ApiUiState{

    data class Success(val photos:String) : ApiUiState

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
            try{
                val listResult = Api.retrofitService.getPhotos()
                apiUiState = ApiUiState.Success("${listResult}")
            }catch (e: IOException){
                apiUiState = ApiUiState.Error
            }
        }
    }
}