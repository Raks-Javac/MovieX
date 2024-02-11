package com.example.moviex.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.moviex.models.MovieData
import com.example.moviex.models.getMovies

@Preview
@Composable
fun MovieRow(
    movie: MovieData = getMovies()[0], onClick: (String) -> Unit = {}
) {
    val expended = remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
//            .height(200.dp)
            .padding(20.dp)
            .clickable {
                onClick(movie.id)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),

        ) {
        Row(

            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .padding(5.dp)
                    .size(120.dp)
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0]).transformations(
                            CircleCropTransformation()
                        )
                        .crossfade(true)
                        .build(),
                    contentDescription = "Image poster",
                    onState = {

                    },

                    )

            }
            Spacer(modifier = Modifier.size(width = 8.dp, height = 1.dp))
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.labelSmall

                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "Released: ${movie.year}",
                    style = MaterialTheme.typography.labelSmall
                )


                //icon to expand
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.clickable {
                        expended.value = !expended.value
                    }
                ) {

                    Text(
                        text =if(!expended.value) "View more" else "View less",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Spacer(modifier = Modifier.width(0.2.dp))
                    Icon(
                        imageVector = if(!expended.value) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                        tint = Color.DarkGray,
                        contentDescription = "Drop down arrow",

                        )
                }

                AnimatedVisibility(visible = expended.value) {
                    Column {
                       Text(text =  buildAnnotatedString {
                           withStyle(
                               style = SpanStyle(
                                   fontSize = 11.sp,
                                   color = Color.DarkGray
                               )

                           ) {
                               append("Plot")
                           }
                           withStyle(
                               style = SpanStyle(
                                   fontSize = 11.sp,
                                   fontWeight = FontWeight.Bold
                               )

                           ) {
                               append("  ${movie.plot}")
                           }
                       })

                    }
                }
            }


        }


    }
}
