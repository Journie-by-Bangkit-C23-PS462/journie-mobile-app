package com.dicoding.journie.ui.screen

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import com.dicoding.journie.ui.components.home.DestinationCard
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
                        contentPadding = PaddingValues(horizontal = 25.dp)
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
                }
            }
        },
        bottomBar = BottomNavigation() {
            
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