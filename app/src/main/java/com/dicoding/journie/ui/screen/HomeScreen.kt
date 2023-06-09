package com.dicoding.journie.ui.screen

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.journie.R
import com.dicoding.journie.data.dummyDestination
import com.dicoding.journie.ui.components.home.BottomBar
import com.dicoding.journie.ui.components.home.DestinationCard
import com.dicoding.journie.ui.components.home.RecommendationCard
import com.dicoding.journie.ui.components.home.SectionTitle
import com.dicoding.journie.ui.theme.JournieTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier, username: String) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 25.dp, top = 12.dp, end = 25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(
                        text = "Selamat datang,",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "${username}!",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Image(
                    painter = painterResource(R.drawable.journie_logo),
                    contentDescription = "Journie Logo",
                    modifier = Modifier
                        .size(height = 20.dp, width = 78.dp)
                )
            }
        },
        modifier = Modifier
            .fillMaxSize(),
        content = {
            Column(
                modifier = modifier
                .padding(paddingValues = it)
            ) {
                Column(
                    modifier = Modifier
                        .padding(25.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.banner),
                        modifier = Modifier
                            .size(width = 340.dp, height = 168.dp),
                        contentDescription = "Banner",
                    )
                }
                Column {
                    SectionTitle(title = "Kota Populer Saat Ini")
                    Spacer(modifier = Modifier.height(12.dp))
                    LazyRow(
                        state = rememberLazyListState(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(horizontal = 25.dp),
                    ) {
                        items(dummyDestination) {
                            DestinationCard(
                                name = it.name,
                                subname = it.subname,
                                description = it.description,
                                urlImage = it.urlImage,
                                modifier = Modifier
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    SectionTitle(title = "Rekomendasi Aktif")
                    RecommendationCard(
                        name = "Bandung",
                        subname = "Jawa Barat",
                        duration = 250,
                        urlImage = "https://th.bing.com/th/id/R.8e0fb11dc04dd053d6cdbcd52dc9bcc6?rik=iD%2foSogxC9oC2g&riu=http%3a%2f%2f2.bp.blogspot.com%2f-DLWPx3Ldj_A%2fVcTOMJ6iwjI%2fAAAAAAAABUY%2fh9c_lfakEos%2fs1600%2fsate.jpg&ehk=5ImaTipkL%2fcjJK%2fTtXOQ4SIHtRIGRk8hU89wPbchHqQ%3d&risl=&pid=ImgRaw&r=0"
                    )
                }
            }
        },
        bottomBar = {
            BottomBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                backgroundColor = Color.Black,
                contentColor = Color.Yellow,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .size(height = 60.dp, width = 180.dp),
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp)
            ) {
                Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "new plan icon", tint = Color.Yellow)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Rencana Baru",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.Yellow
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    JournieTheme {
        HomeScreen(username = "Orang Ganteng")
    }
}