package com.example.moviex.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.moviex.models.MovieData
import com.example.moviex.models.getMovies
import com.example.moviex.navigation.MovieNavigation
import com.example.moviex.navigation.MovieScreens
import com.example.moviex.widgets.MovieRow
val movies: List<MovieData> = getMovies()
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    // A surface container using the 'background' color from the theme
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = "Movies",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.White
                        )
                    )
                })
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column {
                LazyColumn {
                    items(movies.size) {
                        MovieRow(movie = movies[it]) {movie->
                            navController.navigate(route = MovieScreens.DetailScreen.name + "/$movie")
                        }
                    }
                }
            }
        }

    }
}




