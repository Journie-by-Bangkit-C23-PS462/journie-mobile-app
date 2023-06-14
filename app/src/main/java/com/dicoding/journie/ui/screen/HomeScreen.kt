package com.dicoding.journie.ui.screen

import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dicoding.journie.CreatePlanActivity
import com.dicoding.journie.R
import com.dicoding.journie.data.local.dummyDestination
import com.dicoding.journie.data.navigation.Screen
import com.dicoding.journie.ui.components.home.BottomBar
import com.dicoding.journie.ui.components.home.DestinationCard
import com.dicoding.journie.ui.components.home.RecommendationCard
import com.dicoding.journie.ui.components.home.SectionTitle
import com.dicoding.journie.ui.theme.JournieTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    username: String,
    age: Int
) {
    val context = LocalContext.current
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 15.dp, end = 15.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            FloatingActionButton(
                onClick = {
                          val intent = Intent(context, CreatePlanActivity::class.java).apply {
                              putExtra(CreatePlanActivity.EXTRA_USERNAME, username)
                              putExtra(CreatePlanActivity.EXTRA_AGE, age)
                          }
                    context.startActivity(intent)
                },
                backgroundColor = Color.Black,
                contentColor = Color.Yellow,
                shape = RoundedCornerShape(5.dp),
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 2.dp)
            ) {
                Row(modifier = Modifier.padding(start = 12.dp, end = 12.dp),verticalAlignment = Alignment.CenterVertically) {
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
    }
    Column {
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
                        title = it.name,
                        subtitle = it.subname,
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
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    JournieTheme {
        HomeScreen(username = "Orang Ganteng", age = 22)
    }
}