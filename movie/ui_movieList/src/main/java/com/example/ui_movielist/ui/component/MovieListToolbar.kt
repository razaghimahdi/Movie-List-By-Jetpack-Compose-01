package com.example.ui_movielist.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
@Preview
fun MovieListToolbar() {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        // color = MaterialTheme.colors.secondary,
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
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
                    Icons.Default.FilterList,
                    contentDescription = "Filter",
                    modifier = Modifier.padding(10.dp),
                    tint = Color.Black
                )

            }

            Spacer(modifier = Modifier.width(10.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .border(1.5.dp, Color(0xFF6573B9), shape = RoundedCornerShape(12.dp)),
                elevation = 0.dp,
                shape = RoundedCornerShape(12.dp),
                backgroundColor = Color(0x549FA4C2),
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Black,
                        modifier = Modifier.padding(8.dp),
                    )
                    Text(
                      //  modifier = Modifier.padding(start = 4.dp),
                        text = "Search",
                        style = MaterialTheme.typography.h4
                            .merge(TextStyle(fontWeight = FontWeight.W600, fontSize = 23.sp)),
                        color = Color(0xFF505050)
                    )
                }
            }


        }
    }

}

