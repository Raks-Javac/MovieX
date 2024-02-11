@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.moviex.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.moviex.models.getMovies
import com.example.moviex.widgets.MovieRow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, movieData: String?) {
    val movieInfo = getMovies().first { it.id == movieData }

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Back",
                            modifier = Modifier.clickable {
                                navController.popBackStack()
                            }
                        )


                    },
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {

                            Text(
                                movieInfo.title,
                            )
                        }


                    }
                )
            },


            ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                MovieRow(
                    movie = movieInfo
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Movie Images",
                    modifier = Modifier.padding(10.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Box(modifier = Modifier.padding(10.dp)) {

                    LazyRow {
                        items(movieInfo.images.size) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(movieInfo.images[it])
                                    .crossfade(true)
                                    .build(),
                                modifier = Modifier
                                    .height(100.dp)
                                    .padding(5.dp),
                                contentDescription = "Image poster",
                                onState = {
                                },
                            )
                        }

                    }
                }
            }
        }
    }

}

