package com.example.ui_movielist.ui

import android.util.Log
import coil.ImageLoader
import androidx.compose.foundation.Image
import coil.compose.rememberImagePainter
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movie_domain.Movie
import com.example.ui_movielist.R


@Composable
fun MovieListItem(
    movie: Movie,
    onSelectHero: (Int) -> Unit,
    imageLoader: ImageLoader,
) {


    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .background(MaterialTheme.colors.surface)
            .clickable {
                Log.i("AppDebug TAG", "MovieListItem movie.id: "+movie.id)
                onSelectHero(movie.id)
            },
        elevation = 0.dp
    ) {


        Row(
            modifier = Modifier
               // .fillMaxSize()
                .padding(all = 10.dp),
           // verticalAlignment = Alignment.CenterVertically,
           // horizontalArrangement = Arrangement.Start
        ) {

            val painter =
                rememberImagePainter("https://image.tmdb.org/t/p/w500${movie.poster_path}",
                    imageLoader = imageLoader,
                    builder = {
                        placeholder(if (isSystemInDarkTheme()) R.drawable.black_background else R.drawable.white_background)
                    })

            Image(
                modifier = Modifier
                    .width(135.dp)
                    //.height(70.dp)
                    .clip(RoundedCornerShape(percent = 20)),
                contentScale = ContentScale.Crop,
                painter = painter,
                contentDescription = movie.title
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 15.dp, top = 5.dp, bottom = 5.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceAround
            ) {


                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h4
                        /*.merge(TextStyle(fontWeight = FontWeight.W600, fontSize = 23.sp))*/,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.subtitle2,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

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

                        Icon(
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp),
                            painter = painterResource(R.drawable.ic_baseline_calendar_today_24),
                            contentDescription = movie.release_date,
                            //tint = Color.Cyan
                        )

                        val dates = movie.release_date.split("-").toTypedArray()
                        Text(
                            modifier = Modifier.padding(start = 4.dp), text = dates[0],
                            style = MaterialTheme.typography.caption,
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
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

                            Icon(Icons.Filled.Group, movie.vote_count.toString())


                            Text(
                                modifier = Modifier.padding(start = 4.dp),
                                text = movie.vote_count.toString(),
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

                            Icon(
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp),
                                painter = painterResource(R.drawable.ic_baseline_star_24),
                                contentDescription = movie.vote_count.toString(),
                                //tint = Color.Cyan
                            )
                            Text(
                                modifier = Modifier.padding(start = 4.dp),
                                text = movie.vote_average.toString(),
                                style = MaterialTheme.typography.caption,
                            )
                        }
                    }


                }


            }



        }


    }

}
