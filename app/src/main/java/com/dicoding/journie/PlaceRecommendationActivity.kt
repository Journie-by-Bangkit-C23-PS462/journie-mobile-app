package com.dicoding.journie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.dicoding.journie.data.network.response.ActivePlanResponse
import com.dicoding.journie.ui.screen.PlanRecommendationScreen
import com.dicoding.journie.ui.theme.JournieTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
class PlaceRecommendationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listOfDays = intent.getParcelableExtra<ActivePlanResponse>(EXTRA_LIST_DESTINATION_PLAN)
        val index = intent.getIntExtra(EXTRA_INDEX, 0)

        setContent {
            JournieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (listOfDays != null) {
                        PlanRecommendationScreen(navController = rememberNavController(), listPlaceFromIntent = listOfDays.data!![index])
                    }
                }
            }
        }
    }

    companion object {
        val EXTRA_LIST_DESTINATION_PLAN = "extra_destination_plan"
        val EXTRA_INDEX = "extra_index"
    }
}