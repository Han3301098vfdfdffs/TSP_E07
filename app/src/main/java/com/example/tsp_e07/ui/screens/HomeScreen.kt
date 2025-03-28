package com.example.tsp_e07.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
        is ApiUiState.Loading -> LoadingScreen(modifier.fillMaxSize())
        is ApiUiState.Success -> PhotoGridScreen(apiUiState.photos)
        is ApiUiState.Error -> ErrorScreen(modifier.fillMaxSize())
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
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Column {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(photo.download_url)
                    //.data(photo.url)
                    .crossfade(true)
                    .build()
                ,
                contentDescription = stringResource(R.string.api_image),
                modifier = modifier.fillMaxWidth(),
                error = painterResource(R.drawable.error_404),
                placeholder = painterResource(R.drawable.carga),
                contentScale = ContentScale.Crop,

                )
            Text(
                text = "autor",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }

    }

}

@Composable
fun PhotoGridScreen(
    photos: List<ApiPhoto>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp),
        contentPadding = contentPadding
    ) {
        items(
            items = photos,
            key = {photo -> photo.id}
        ){
                photo -> ApiPhotoCard(
            photo = photo,
            modifier = modifier
                .padding(4.dp)
                .fillMaxWidth()
                .aspectRatio(1.5f)
        )
        }
    }
}
