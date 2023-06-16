package com.dicoding.journie.ui.screen

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.journie.CreatePlanActivity
import com.dicoding.journie.data.navigation.Screen
import com.dicoding.journie.ui.components.PrimaryButton
import com.dicoding.journie.ui.components.place.ListOfPlacesScreen
import com.dicoding.journie.ui.theme.JournieTheme
import com.dicoding.journie.viewmodel.DestinationViewModel
import com.dicoding.journie.viewmodel.DestinationViewModelFactory
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun PlanRecommendationScreen(
    navController: NavHostController
) {
    val activityContext = LocalContext.current as Activity
    var tabIndex by remember { mutableStateOf(0) }
    val destinationViewModel : DestinationViewModel = viewModel(factory = DestinationViewModelFactory.getInstance())
    val listPlaces by destinationViewModel.planModelList.collectAsState()
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Rekomendasi Buat Kamu") },
            navigationIcon = {
                IconButton(
                    onClick = {
                        navController.popBackStack(Screen.CreatePlan.route, false)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "icon to return previous page"
                    )
                }
            },
            backgroundColor = Color.White,
            elevation = 0.dp
        )
    }) {
        Box(modifier = Modifier.padding(it)) {
            Column(modifier = Modifier.fillMaxSize()) {
                TabRow(
                    selectedTabIndex = tabIndex,
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                ) {
                    listPlaces.forEachIndexed { index, _ ->
                        Tab(
                            selected = tabIndex == index,
                            onClick = { tabIndex = index },
                            selectedContentColor = Color.White,
                            unselectedContentColor = Color.Gray,
                            text = { Text(text = "Hari ke-${index + 1}")}
                        )
                    }
                }
                listPlaces.forEachIndexed { index, destinations ->
                    if (tabIndex == index) {
                        ListOfPlacesScreen(destinationPerDays = destinations)
                    }
                }
            }
        }
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(12.dp)
                        .shadow(1.dp)
                        .background(Color.LightGray)
                ) {
                    PrimaryButton(
                        onClick = {

                        },
                        label = "Simpan Rekomendasi"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun PlanRecommendationPreview() {
    JournieTheme {
        PlanRecommendationScreen(navController = rememberNavController())
    }
}