package com.example.tsp_e07.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tsp_e07.R
import com.example.tsp_e07.ui.screens.HomeScreen
import com.example.tsp_e07.viewmodel.ApiViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApiApp(){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {ApiTopBar(scrollBehavior = scrollBehavior)}
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            //HomeScreen(apiUiState = "photos", contentPadding = it)
            val apiViewModel: ApiViewModel = viewModel()
            HomeScreen(apiUiState = apiViewModel.apiUiState, contentPadding = it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApiTopBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.api_app),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    )
}