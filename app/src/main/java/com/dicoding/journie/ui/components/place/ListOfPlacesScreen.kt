package com.dicoding.journie.ui.components.place

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dicoding.journie.data.network.response.DestinationRecommendation
import com.dicoding.journie.ui.components.home.RecommendationCard

@Composable
fun ListOfPlacesScreen(
    destinationPerDays: List<DestinationRecommendation>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        state = rememberLazyListState(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(destinationPerDays) {
            RecommendationCard(
                name = it.placeName.toString(),
                subname = it.city.toString(),
                duration = it.duration!!,
                urlImage = it.imageLink.toString(),
                description = it.description.toString(),
                category = it.category.toString(),
                latitude = it.lat!!,
                longitude = it.long!!,
                city = it.city.toString()
            )
        }
    }
}