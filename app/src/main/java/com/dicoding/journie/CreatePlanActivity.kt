package com.dicoding.journie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.dicoding.journie.ui.theme.JournieTheme

class CreatePlanActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val age = intent.getIntExtra(EXTRA_AGE, 0)

        setContent {
            JournieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    CreatePlanScreen(username = username.toString(), age = age)
                }
            }
        }
    }

    companion object {
        val EXTRA_USERNAME = "extra_username"
        val EXTRA_AGE = "extra_age"
    }
}
