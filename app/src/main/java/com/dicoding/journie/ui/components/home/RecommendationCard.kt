package com.dicoding.journie.ui.components.home

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dicoding.journie.DetailDestinationActivity
import com.dicoding.journie.ui.theme.JournieTheme

@Composable
fun RecommendationCard(
    modifier: Modifier = Modifier.padding(top = 12.dp, bottom = 12.dp, start = 25.dp, end = 25.dp),
    name : String,
    subname : String,
    duration: Int = 0,
    urlImage: String,
    description: String = "",
    category: String = "",
    rating: Double = 0.0,
    score: Int = 0,
    latitude: Double = 0.0,
    longitude: Double = 0.0,
    city: String = "",
) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable {
                val intent = Intent(context, DetailDestinationActivity::class.java).apply {
                    putExtra(DetailDestinationActivity.EXTRA_PLACENAME, name)
                    putExtra(DetailDestinationActivity.EXTRA_URL_IMAGE, urlImage)
                    putExtra(DetailDestinationActivity.EXTRA_DESCRIPTION, description)
                    putExtra(DetailDestinationActivity.EXTRA_CATEGORY, category)
                    putExtra(DetailDestinationActivity.EXTRA_RATING, rating)
                    putExtra(DetailDestinationActivity.EXTRA_SCORE, score)
                    putExtra(DetailDestinationActivity.EXTRA_LATITUDE, latitude)
                    putExtra(DetailDestinationActivity.EXTRA_LONGITUDE, longitude)
                    putExtra(DetailDestinationActivity.EXTRA_CITY, city)
                }
                context.startActivity(intent)
            }
        ,
        shape = RoundedCornerShape(5.dp),
        backgroundColor = Color.White,
        elevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Box(modifier = Modifier
                .fillMaxHeight()
                .width(100.dp)
            ) {
                AsyncImage(
                    model = urlImage,
                    contentDescription = "recommendation image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column() {
                        Text(
                            text = name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = subname,
                            fontSize = 10.sp,
                            color = Color.Gray,
                        )
                    }
                    if (duration > 0) {
                        Column {
                            Text(
                                text = "Durasi",
                                fontSize = 10.sp,
                            )
                            Text(
                                text = "${duration} Menit",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationCardPreview(){
    JournieTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)) {
            RecommendationCard(
                name = "Kota Nama Panjang Banget",
                subname = "Kota Nama Panjang Banget",
                duration = 250,
                urlImage = "https://th.bing.com/th/id/R.8e0fb11dc04dd053d6cdbcd52dc9bcc6?rik=iD%2foSogxC9oC2g&riu=http%3a%2f%2f2.bp.blogspot.com%2f-DLWPx3Ldj_A%2fVcTOMJ6iwjI%2fAAAAAAAABUY%2fh9c_lfakEos%2fs1600%2fsate.jpg&ehk=5ImaTipkL%2fcjJK%2fTtXOQ4SIHtRIGRk8hU89wPbchHqQ%3d&risl=&pid=ImgRaw&r=0"
            )
        }
    }
}