package com.dicoding.journie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dicoding.journie.data.navigation.Screen
import com.dicoding.journie.ui.components.home.BottomBar
import com.dicoding.journie.ui.screen.ExploreScreen
import com.dicoding.journie.ui.screen.HomeScreen
import com.dicoding.journie.ui.screen.ProfileScreen
import com.dicoding.journie.ui.theme.JournieTheme

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

                }
            }
        }
    }
}

@Composable
fun JournieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    username: String,
    email: String,
    age: Int,
    isMale: Boolean
) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController)}) { paddingValues ->
        NavHost(navController = navController, startDestination = Screen.Home.route, modifier = Modifier.padding(paddingValues)) {
            composable(Screen.Home.route) {
                HomeScreen(username = username, age = age)
            }
            composable(Screen.Explore.route) {
                ExploreScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen(username = username, email = email, age = age, isMale = isMale)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    JournieTheme {
        JournieApp(username = "Orang Ganteng", email = "ganteng@mail.com", age = 25, isMale = true)
    }
}