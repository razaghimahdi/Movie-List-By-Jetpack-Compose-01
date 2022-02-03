package com.example.ui_moviedetail.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import com.example.movie_domain.Movie
import com.example.ui_moviedetail.R

@Composable
fun BodyDetail(
    movie: Movie,
    imageLoader: ImageLoader,) {

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        elevation = 0.dp,
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {

            val painter =
                rememberImagePainter(
                    "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                    imageLoader = imageLoader,
                     builder = {
                         placeholder(if (isSystemInDarkTheme()) R.drawable.black_background else R.drawable.white_background)
                     }
                )

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(horizontal = 32.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(percent = 20)),
                contentScale = ContentScale.Crop,
                painter = painter,
                contentDescription = null
            )


                Text(
                    modifier = Modifier
                        .padding(horizontal = 32.dp),
                    text = movie.original_title,
                    style = MaterialTheme.typography.h4,
                )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp)
                    .horizontalScroll(rememberScrollState()),

            //    verticalAlignment = Alignment.CenterVertically
            ) {


                Card(
                    elevation = 0.dp,
                    shape = RoundedCornerShape(25.dp),
                    backgroundColor = Color(0x549FA4C2),
                    ) {
                    Row(
                        modifier = Modifier.padding(all = 12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Filled.CalendarToday, null)


                        Text(
                            modifier = Modifier.padding(start = 4.dp), text = movie.release_date,
                            style = MaterialTheme.typography.caption,
                        )
                    }
                }


                Spacer(modifier = Modifier.width(10.dp))

                Card(
                    elevation = 0.dp,
                    shape = RoundedCornerShape(25.dp),
                    backgroundColor = Color(0x549FA4C2),
                    ) {
                    Row(
                        modifier = Modifier.padding(all = 12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(Icons.Filled.Group, null)


                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text =  movie.vote_count.toString() ,
                            style = MaterialTheme.typography.caption,
                        )
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                Card(
                    elevation = 0.dp,
                    shape = RoundedCornerShape(25.dp),
                    backgroundColor = Color(0x549FA4C2),

                    ) {
                    Row(
                        modifier = Modifier.padding(all = 12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Filled.Star, null)

                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = movie.vote_average.toString(),
                            style = MaterialTheme.typography.caption,
                        )
                    }
                }



                Spacer(modifier = Modifier.width(10.dp))

                Card(
                    elevation = 0.dp,
                    shape = RoundedCornerShape(25.dp),
                    backgroundColor = Color(0x549FA4C2),

                    ) {
                    Row(
                        modifier = Modifier.padding(all = 12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Filled.Language, null)

                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = movie.original_language,
                            style = MaterialTheme.typography.caption,
                        )
                    }
                }


            }


                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    text = movie.title,
                    style = MaterialTheme.typography.h6,
                )

                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp,vertical = 8.dp),
                    text = movie.overview,
                    style = MaterialTheme.typography.caption,
                )



        }


    }
}