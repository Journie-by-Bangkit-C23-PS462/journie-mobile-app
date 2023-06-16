package com.dicoding.journie.ui.screen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.journie.data.navigation.Screen
import com.dicoding.journie.data.network.response.DestinationRecommendation
import com.dicoding.journie.ui.components.PrimaryButton
import com.dicoding.journie.ui.components.place.ListOfPlacesScreen
import com.dicoding.journie.ui.theme.JournieTheme
import com.dicoding.journie.viewmodel.DestinationViewModel
import com.dicoding.journie.viewmodel.DestinationViewModelFactory
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun PlanRecommendationScreen(
    navController: NavHostController,
    listPlaceFromIntent: List<List<DestinationRecommendation>> = emptyList()
) {
    var tabIndex by remember { mutableStateOf(0) }
    val destinationViewModel : DestinationViewModel = viewModel(factory = DestinationViewModelFactory.getInstance())
    var planIDs by remember { mutableStateOf(0) }
    val planID by destinationViewModel.planModelID.collectAsState()
    val listPlaces by destinationViewModel.planModelList.collectAsState()
    val savePlanResponse by destinationViewModel.savePlanResponse.collectAsState()
    var saveStatus by remember { mutableStateOf(false) }
    val context = LocalContext.current as Activity
    if (savePlanResponse == "Plan Saved") {
        navController.popBackStack(Screen.Home.route, inclusive = false)
    }

    Scaffold(
        topBar = {
        TopAppBar(
            title = { Text(text = "Rekomendasi Buat Kamu") },
            navigationIcon = {
                IconButton(
                    onClick = {
                        if (listPlaceFromIntent.isNotEmpty()) {
                            context.finish()
                        } else {
                            navController.popBackStack(Screen.CreatePlan.route, false)
                        }
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
        ) },
        bottomBar = {
            if (listPlaceFromIntent.isEmpty()) {
                BottomAppBar(modifier = Modifier.padding(start = 25.dp, end = 25.dp, bottom = 8.dp, top = 4.dp).background(Color.White)) {
                    if (saveStatus) {
                        PrimaryButton(
                            onClick = {},
                            modifier = Modifier
                                .background(Color.LightGray),
                            content = {
                                Text(
                                    text = "Tunggu Sebentar",
                                    color = MaterialTheme.colors.secondary,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                CircularProgressIndicator(backgroundColor = Color.Black)
                            },
                            enabled = false
                        )
                    } else {
                        PrimaryButton(
                            onClick = {
                                saveStatus = true
                                destinationViewModel.savePlanModel(planID)
                                navController.navigate(Screen.Home.route)
                            },
                            content = {
                                Text(
                                    text = "Simpan Rekomendasi Ini",
                                    color = MaterialTheme.colors.secondary,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                            }
                        )
                    }
                }
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(modifier = Modifier.fillMaxSize()) {
                if (listPlaceFromIntent.isNullOrEmpty()) {
                    if ( listPlaces.isNullOrEmpty() ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Memuat data..")
                            Spacer(modifier = Modifier.height(8.dp))
                            CircularProgressIndicator()
                        }
                    } else {
                        if ( planID != 0 ) {
                            planIDs = planID
                        }
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
                } else {
                    TabRow(
                        selectedTabIndex = tabIndex,
                        backgroundColor = Color.Black,
                        contentColor = Color.White
                    ) {
                        listPlaceFromIntent.forEachIndexed { index, _ ->
                            Tab(
                                selected = tabIndex == index,
                                onClick = { tabIndex = index },
                                selectedContentColor = Color.White,
                                unselectedContentColor = Color.Gray,
                                text = { Text(text = "Hari ke-${index + 1}")}
                            )
                        }
                    }
                    listPlaceFromIntent.forEachIndexed { index, destinations ->
                        if (tabIndex == index) {
                            ListOfPlacesScreen(destinationPerDays = destinations)
                        }
                    }
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