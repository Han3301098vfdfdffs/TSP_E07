package com.example.tsp_e07.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tsp_e07.R
import com.example.tsp_e07.model.ApiPhoto
import com.example.tsp_e07.viewmodel.ApiUiState

@Composable
fun HomeScreen(
    apiUiState: ApiUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
    ){
    when(apiUiState){
        is ApiUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is ApiUiState.Success -> ApiPhotoCard(photo = apiUiState.photo, modifier = modifier.fillMaxSize())
        is ApiUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}

@Composable

fun LoadingScreen(modifier: Modifier = Modifier){
    Box(modifier = modifier,
        contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(id = R.drawable.loader),
            contentDescription = "Loading"
        )
    }
}
@Composable
fun ResultScreen(photos: String,modifier: Modifier = Modifier){
    Box(modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Text(text = photos)
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier){
    Box(modifier = modifier,
        contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(id = R.drawable.error),
            contentDescription = "Error"
        )
    }
}

@Composable
fun ApiPhotoCard(photo: ApiPhoto, modifier: Modifier){
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(photo.download_url)
            //.data(photo.url)
            .crossfade(true)
            .build()
        ,
        contentDescription = stringResource(R.string.api_image),
        modifier = modifier
    )
}
//@Preview
//@Composable
//fun HomeScreenPreview(){
//    Surface(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        HomeScreen(stringResource(R.string.placeholder_result))
//    }
//}