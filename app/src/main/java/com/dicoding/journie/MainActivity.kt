package com.dicoding.journie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.*
import androidx.navigation.compose.*
import com.dicoding.journie.data.navigation.Screen
import com.dicoding.journie.ui.components.home.BottomBar
import com.dicoding.journie.ui.screen.*
import com.dicoding.journie.ui.theme.JournieTheme
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JournieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    JournieApp(username = "Bangkit Reviewer", age = 25)
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun JournieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    username: String,
    age: Int,
) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController)}) { paddingValues ->
        NavHost(navController = navController, startDestination = Screen.Home.route, modifier = Modifier.padding(paddingValues)) {
            composable(Screen.Home.route) {
                HomeScreen(username = username, navController = navController)
            }
            composable(Screen.Explore.route) {
                ExploreScreen()
            }
//            composable(Screen.Profile.route) {
//                ProfileScreen(username = username, email = email, age = age, isMale = isMale)
//            }
            composable(Screen.CreatePlan.route) {
                CreatePlanScreen(username = username, age = age, navController = navController)
            }
            composable(Screen.RecommendationPlan.route) {
                PlanRecommendationScreen(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    JournieTheme {
        JournieApp(username = "Tech Enthusiast", age = 25)
    }
}