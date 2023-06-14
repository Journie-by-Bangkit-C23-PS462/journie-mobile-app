package com.dicoding.journie.ui.screen

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dicoding.journie.R
import com.dicoding.journie.ui.components.place.VisitMapButton
import com.dicoding.journie.ui.theme.JournieTheme

@Composable
fun PlaceScreen(
    modifier: Modifier = Modifier,
    title: String,
    urlImage: String,
    description: String,
    category: String,
    rating: Double,
    score: Int,
    latitude: Double,
    longitude: Double,
    city: String
) {
    val context = LocalContext.current as Activity
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                        Text(text = "Detail Destinasi")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        context.finish()
                    }) { Icon(Icons.Filled.ArrowBack," icon back to the previous page")}
                },
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        },
    ) {
        Column(modifier = modifier.padding(it)) {
            AsyncImage(
                model = urlImage,
                contentDescription = "place image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier
                .padding(start = 25.dp, end = 25.dp, top = 15.dp)
                .verticalScroll(
                    rememberScrollState()
                )) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${category} di ${city}",
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.baseline_star_24), contentDescription = "star icon", tint = Color.Yellow)
                    Text(text = "Rating: $rating (${score})", fontSize = 12.sp, color = Color.Gray)
                }
                Spacer(modifier = Modifier.height(18.dp))
                Text(text = description, lineHeight = 30.sp)
                Spacer(modifier = Modifier.height(20.dp))
                VisitMapButton(latitude = latitude, longitude = longitude, placeName = title)
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceScreenPreview() {
    JournieTheme {
        PlaceScreen(
            title = "Gereja Perawan Maria Tak Berdosa Surabaya",
            urlImage = "https://media.travelingyuk.com/unggah/kurasi/2019/02/Gereja_Santa_Perawan_Maria_1_0483.jpg",
            description = "Gereja Katolik Kelahiran Santa Perawan Maria merupakan salah satu gereja tua di kota Surabaya, Jawa Timur, Indonesia. Berlokasi di Jalan Kepanjen, Surabaya, bangunan religius ini berdampingan dengan gedung SMA Katolik Frateran Surabaya.\\r",
            category = "Tempat Ibadah",
            rating = 4.8,
            score = 31,
            latitude = -7.2420758,
            longitude = 112.7368158,
            city = "Surabaya"
        )
    }
}