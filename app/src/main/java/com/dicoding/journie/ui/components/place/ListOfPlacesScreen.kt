package com.dicoding.journie.ui.components.place

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dicoding.journie.data.network.response.Destination
import com.dicoding.journie.ui.components.home.RecommendationCard

@Composable
fun ListOfPlacesScreen(
    destinationPerDays: List<Destination>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 25.dp, end = 25.dp),
        state = rememberLazyListState(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(destinationPerDays) {
//            RecommendationCard(name = it.placeName, subname = it.city, duration = , urlImage = )
        }
    }
}