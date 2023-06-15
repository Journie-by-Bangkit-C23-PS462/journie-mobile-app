package com.dicoding.journie.ui.screen

import android.app.Activity
import android.app.Dialog
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.dicoding.journie.data.navigation.TabItem
import com.dicoding.journie.data.network.response.CreatePlanResponse
import com.dicoding.journie.data.network.response.Destination
import com.dicoding.journie.data.network.response.DestinationPlan
import com.dicoding.journie.ui.theme.JournieTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun PlanRecommendationScreen(
    planTrips: CreatePlanResponse
) {
    var openDialog by remember { mutableStateOf(false) }
    val activityContext = LocalContext.current as Activity
    var tabIndex by remember { mutableStateOf(0) }
    val destinationsPerDay = mutableListOf<List<DestinationPlan>>(emptyList())
    planTrips.data.forEach {
        destinationsPerDay.add(it)
    }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Rekomendasi Buat Kamu") },
            navigationIcon = {
                IconButton(
                    onClick = {
                        activityContext.finish()
                        activityContext.finish()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "icon to return previous page"
                    )
                }
            })
    }) {
        Box(modifier = Modifier.padding(it)) {
            Column(modifier = Modifier.fillMaxSize()) {
                TabRow(selectedTabIndex = tabIndex) {
                    planTrips.data.forEachIndexed { index, _ ->
                        Tab(
                            selected = tabIndex == index,
                            onClick = { tabIndex = index }
                        )
                    }
                }
                planTrips.data.forEachIndexed { index, destinations ->
//                    if (tabIndex == index) {
//
//                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanRecommendationPreview() {
    JournieTheme {
        
    }
}