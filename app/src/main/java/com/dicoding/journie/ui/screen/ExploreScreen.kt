package com.dicoding.journie.ui.screen


import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.journie.DetailDestinationActivity
import com.dicoding.journie.data.network.response.Destination
import com.dicoding.journie.ui.components.home.BottomBar
import com.dicoding.journie.ui.components.home.DestinationCard
import com.dicoding.journie.ui.components.home.SectionTitle
import com.dicoding.journie.ui.theme.JournieTheme
import com.dicoding.journie.viewmodel.DestinationViewModel
import com.dicoding.journie.viewmodel.DestinationViewModelFactory

@Composable
fun ExploreScreen() {
    val destinationViewModel : DestinationViewModel = viewModel(factory = DestinationViewModelFactory.getInstance())
    val jakartaPlaces by destinationViewModel.jakartaPlacesList.collectAsState()
    val bandungPlaces by destinationViewModel.bandungPlacesList.collectAsState()
    val surabayaPlaces by destinationViewModel.surabayaPlacesList.collectAsState()
    val semarangPlaces by destinationViewModel.semarangPlacesList.collectAsState()
    val jogjaPlaces by destinationViewModel.jogjaPlacesList.collectAsState()
    val context = LocalContext.current
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Eksplor Destinasi",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(start = 25.dp, top = 15.dp, bottom = 15.dp)
            )
            if (jakartaPlaces.isNullOrEmpty()) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .padding(start = 25.dp, end = 25.dp)
                    .align(alignment = Alignment.CenterHorizontally)) {
                    CircularProgressIndicator()
                }
            } else {
                SectionTitle(title = "Jakarta")
                Spacer(modifier = Modifier.height(12.dp))
                ShowDestinations(jakartaPlaces, context)
                Spacer(modifier = Modifier.height(24.dp))
            }
            if (bandungPlaces.isNullOrEmpty()) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .padding(start = 25.dp, end = 25.dp)
                    .align(alignment = Alignment.CenterHorizontally)) {
                    CircularProgressIndicator()
                }
            } else {
                SectionTitle(title = "Bandung")
                Spacer(modifier = Modifier.height(12.dp))
                ShowDestinations(bandungPlaces, context)
                Spacer(modifier = Modifier.height(24.dp))
            }
            if (surabayaPlaces.isNullOrEmpty()) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .padding(start = 25.dp, end = 25.dp)
                    .align(alignment = Alignment.CenterHorizontally)) {
                    CircularProgressIndicator()
                }
            } else {
                SectionTitle(title = "Surabaya")
                Spacer(modifier = Modifier.height(12.dp))
                ShowDestinations(surabayaPlaces, context)
                Spacer(modifier = Modifier.height(24.dp))
            }
            if (semarangPlaces.isNullOrEmpty()) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .padding(start = 25.dp, end = 25.dp)
                    .align(alignment = Alignment.CenterHorizontally)) {
                    CircularProgressIndicator()
                }
            } else {
                SectionTitle(title = "Semarang")
                Spacer(modifier = Modifier.height(12.dp))
                ShowDestinations(semarangPlaces, context)
                Spacer(modifier = Modifier.height(24.dp))
            }
            if (jogjaPlaces.isNullOrEmpty()) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .padding(start = 25.dp, end = 25.dp)
                    .align(alignment = Alignment.CenterHorizontally)) {
                    CircularProgressIndicator()
                }
            } else {
                SectionTitle(title = "Yogyakarta")
                Spacer(modifier = Modifier.height(12.dp))
                ShowDestinations(jogjaPlaces, context)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ShowDestinations(listDestinations: List<Destination>, context: Context) {
    LazyRow(
        state = rememberLazyListState(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 25.dp)
    ) {
        items(listDestinations) {
            DestinationCard(
                title = it.placeName.toString(),
                subtitle = it.category.toString(),
                urlImage = it.imageLink.toString(),
                modifier = Modifier,
                placeName = it.placeName.toString(),
                description = it.description.toString(),
                category = it.category.toString(),
                rating = it.rating!!.toDouble(),
                score = it.score!!.toInt(),
                latitude = it.lat!!.toDouble(),
                longitude = it.long!!.toDouble(),
                city = it.city.toString(),
                onClick = {
                    val intent = Intent(context, DetailDestinationActivity::class.java).apply {
                        putExtra(DetailDestinationActivity.EXTRA_PLACENAME, it.placeName)
                        putExtra(DetailDestinationActivity.EXTRA_URL_IMAGE, it.imageLink)
                        putExtra(DetailDestinationActivity.EXTRA_DESCRIPTION, it.description)
                        putExtra(DetailDestinationActivity.EXTRA_CATEGORY, it.category)
                        putExtra(DetailDestinationActivity.EXTRA_RATING, it.rating)
                        putExtra(DetailDestinationActivity.EXTRA_SCORE, it.score)
                        putExtra(DetailDestinationActivity.EXTRA_LATITUDE, it.lat)
                        putExtra(DetailDestinationActivity.EXTRA_LONGITUDE, it.long)
                        putExtra(DetailDestinationActivity.EXTRA_CITY, it.city)
                    }
                    context.startActivity(intent)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview() {
    JournieTheme {
        ExploreScreen()
    }
}