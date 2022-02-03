package com.example.ui_moviedetail.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun MovieDetailToolbar(){
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        // color = MaterialTheme.colors.secondary,
        elevation = 0.dp,
    ) {
        Box(
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
          //  horizontalArrangement = Arrangement.End
        ) {


            Card(
                modifier = Modifier
                    .width(45.dp)
                    .height(45.dp)
                    .border(1.5.dp, Color(0xFF6573B9), shape = RoundedCornerShape(12.dp)),
                elevation = 0.dp,
                shape = RoundedCornerShape(12.dp),
                backgroundColor = Color(0x549FA4C2),
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.padding(10.dp),
                    tint = Color.Black
                )

            }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        //  modifier = Modifier.padding(start = 4.dp),
                        text = "Detail",
                        style = MaterialTheme.typography.h4
                            .merge(TextStyle(fontWeight = FontWeight.W600, fontSize = 23.sp)),
                        color = Color(0xFF505050)
                    )
                }


        }
    }


}