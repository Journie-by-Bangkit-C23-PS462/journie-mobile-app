package com.dicoding.journie.ui.screen

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.journie.ui.components.PrimaryButton
import com.dicoding.journie.ui.components.home.DropdownChoice
import com.dicoding.journie.ui.components.home.PreferenceTripCheckBox
import com.dicoding.journie.ui.theme.JournieTheme

@Composable
fun CreatePlanScreen(
    modifier: Modifier = Modifier,
    username: String,
    age: Int
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
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                        Text(text = "Buat Rencana")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        context.finish()
                    }) { Icon(Icons.Filled.ArrowBack," icon back to the previous page")}
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
                    val days = listOf("1","2","3")
                    Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                        Text(text = "Kota", fontWeight = FontWeight.Medium)
                        Spacer(modifier = Modifier.height(12.dp))
                        DropdownChoice(
                            values = cities,
                            valueState = cityState,
                            onValueChange = { newCityValue ->
                                cityState = newCityValue
                            }
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Durasi Hari")
                        Spacer(modifier = Modifier.height(12.dp))
                        DropdownChoice(
                            values = days,
                            valueState = durationState,
                            onValueChange = { newDurationValue ->
                                durationState = newDurationValue
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val preferences = arrayListOf<String>(
                        "Bahari",
                        "Budaya",
                        "Taman Hiburan",
                        "Cagar Alam",
                        "Pusat Perbelanjaan",
                        "Tempat Ibadah"
                    )
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
                PrimaryButton(
                    onClick = {
                              // TODO: Insert planning recommendation logic here
                    },
                    modifier = Modifier
                        .padding(top = 15.dp),
                    label = "Rekomendasikan Aku!"
                )
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
            age = 21
        )
    }
}