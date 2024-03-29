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
import com.dicoding.journie.ui.components.PrimaryButton
import com.dicoding.journie.ui.components.home.DropdownChoice
import com.dicoding.journie.ui.components.home.PreferenceTripCheckBox
import com.dicoding.journie.ui.theme.JournieTheme
import com.dicoding.journie.viewmodel.DestinationViewModel
import com.dicoding.journie.viewmodel.DestinationViewModelFactory

@Composable
fun CreatePlanScreen(
    modifier: Modifier = Modifier,
    username: String,
    age: Int,
    navController: NavHostController
) {
    val context = LocalContext.current as Activity
    var checkedBahariState by remember { mutableStateOf(false) }
    var checkedBudayaState by remember { mutableStateOf(false) }
    var checkedTamanHiburanState by remember { mutableStateOf(false) }
    var checkedCagarAlamState by remember { mutableStateOf(false) }
    var checkedPusatPerbelanjaanState by remember { mutableStateOf(false) }
    var checkedTempatIbadah by remember { mutableStateOf(false) }
    var cityState by remember { mutableStateOf("-") }
    var durationState by remember { mutableStateOf("-") }

    var submitStatus by remember { mutableStateOf(false) }

    val destinationViewModel : DestinationViewModel = viewModel(factory = DestinationViewModelFactory.getInstance())

    val recommendationStatus by destinationViewModel.planModelStatus.collectAsState()
    var alreadyNavigateStatus by remember { mutableStateOf(recommendationStatus) }

    if (recommendationStatus) {
        submitStatus = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Buat Rencana")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack(route = Screen.Home.route, inclusive = false)
                    }) { Icon(Icons.Filled.ArrowBack, " icon back to the previous page") }
                },
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        },
    ) {
        Box(modifier = modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 25.dp, end = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val cities = listOf(
                        "Bandung",
                        "Jakarta",
                        "Semarang",
                        "Surabaya",
                        "Yogyakarta"
                    )
                    val days = listOf("1", "2", "3")
                    Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                        Text(text = "Kota", fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.height(12.dp))
                        DropdownChoice(
                            items = cities,
                            selectedItem = cityState,
                            onItemSelected = { cityState = it })
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Durasi Hari", fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.height(12.dp))
                        DropdownChoice(
                            items = days,
                            selectedItem = durationState,
                            onItemSelected = { durationState = it }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Preferensi Tempat Wisata", fontWeight = FontWeight.SemiBold)
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                            PreferenceTripCheckBox(
                                label = "Bahari",
                                preference = checkedBahariState,
                                onPreferenceStateChange = { checkedBahariState = it }
                            )
                            PreferenceTripCheckBox(
                                label = "Budaya",
                                preference = checkedBudayaState,
                                onPreferenceStateChange = { checkedBudayaState = it }
                            )
                            PreferenceTripCheckBox(
                                label = "Taman Hiburan",
                                preference = checkedTamanHiburanState,
                                onPreferenceStateChange = { checkedTamanHiburanState = it }
                            )
                        }
                        Spacer(modifier = Modifier.width(15.dp))
                        Column(modifier = Modifier.fillMaxWidth()) {
                            PreferenceTripCheckBox(
                                label = "Cagar Alam",
                                preference = checkedCagarAlamState,
                                onPreferenceStateChange = { checkedCagarAlamState = it }
                            )
                            PreferenceTripCheckBox(
                                label = "Pusat Perbelanjaan",
                                preference = checkedPusatPerbelanjaanState,
                                onPreferenceStateChange = { checkedPusatPerbelanjaanState = it }
                            )
                            PreferenceTripCheckBox(
                                label = "Tempat Ibadah",
                                preference = checkedTempatIbadah,
                                onPreferenceStateChange = { checkedTempatIbadah = it }
                            )
                        }
                    }
                }

                if(submitStatus) {
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
                            submitStatus = true
                            destinationViewModel.createPlanModel(
                                city = cityState,
                                duration = durationState.toInt(),
                                age = age,
                                username = username,
                                bahari = checkedBahariState,
                                budaya = checkedBudayaState,
                                tamanHiburan = checkedTamanHiburanState,
                                cagarAlam = checkedCagarAlamState,
                                pusatPerbelanjaan = checkedPusatPerbelanjaanState,
                                tempatIbadah = checkedTempatIbadah
                            )
                            navController.navigate(Screen.RecommendationPlan.route)
                        },
                        modifier = Modifier
                            .padding(top = 15.dp),
                        content = {
                            Text(
                                text = "Cari Rekomendasi",
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
}


@Preview(showBackground = true)
@Composable
fun CreatePlanPreview() {
    JournieTheme {
        CreatePlanScreen(
            username = "Robert",
            age = 21,
            navController = rememberNavController()
        )
    }
}