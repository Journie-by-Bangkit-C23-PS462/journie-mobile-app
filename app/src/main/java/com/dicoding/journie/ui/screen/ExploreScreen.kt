package com.dicoding.journie.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.journie.data.network.response.Destination
import com.dicoding.journie.ui.components.home.DestinationCard
import com.dicoding.journie.ui.components.home.SectionTitle
import com.dicoding.journie.ui.theme.JournieTheme
import com.dicoding.journie.viewmodel.DestinationViewModel
import com.dicoding.journie.viewmodel.DestinationViewModelFactory

@Composable
fun ExploreScreen(
    destinationViewModel: DestinationViewModel
) {
    val jakartaPlaces by destinationViewModel.jakartaPlacesList.collectAsState()
    val bandungPlaces by destinationViewModel.bandungPlacesList.collectAsState()
    val surabayaPlaces by destinationViewModel.surabayaPlacesList.collectAsState()
    val semarangPlaces by destinationViewModel.semarangPlacesList.collectAsState()
    val jogjaPlaces by destinationViewModel.jogjaPlacesList.collectAsState()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            Text(
                text = "Eksplor Destinasi",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(start = 25.dp, top = 15.dp)
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 15.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    SectionTitle(title = "Jakarta")
                    Spacer(modifier = Modifier.height(12.dp))
                    ShowDestinations(jakartaPlaces)
                    Spacer(modifier = Modifier.height(24.dp))
                    SectionTitle(title = "Bandung")
                    Spacer(modifier = Modifier.height(12.dp))
                    ShowDestinations(bandungPlaces)
                    Spacer(modifier = Modifier.height(24.dp))
                    SectionTitle(title = "Surabaya")
                    Spacer(modifier = Modifier.height(12.dp))
                    ShowDestinations(surabayaPlaces)
                    Spacer(modifier = Modifier.height(24.dp))
                    SectionTitle(title = "Semarang")
                    Spacer(modifier = Modifier.height(12.dp))
                    ShowDestinations(semarangPlaces)
                    Spacer(modifier = Modifier.height(24.dp))
                    SectionTitle(title = "Yogyakarta")
                    Spacer(modifier = Modifier.height(12.dp))
                    ShowDestinations(jogjaPlaces)
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    )
}

@Composable
fun ShowDestinations(listDestinations: List<Destination>) {
    LazyRow(
        state = rememberLazyListState(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 25.dp)
    ) {
        items(listDestinations) {
            DestinationCard(
                name = it.placeName.toString(),
                subname = it.category.toString(),
                description = it.description.toString(),
                urlImage = it.imageLink.toString(),
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExploreScreenPreview() {
    val destinationViewModel : DestinationViewModel = viewModel(factory = DestinationViewModelFactory.getInstance())
    JournieTheme {
        ExploreScreen(destinationViewModel = destinationViewModel)
    }
}