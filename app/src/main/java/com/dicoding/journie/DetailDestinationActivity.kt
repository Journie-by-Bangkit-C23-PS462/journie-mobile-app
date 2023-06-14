package com.dicoding.journie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dicoding.journie.ui.screen.PlaceScreen
import com.dicoding.journie.ui.theme.JournieTheme

class DetailDestinationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val placeName = intent.getStringExtra(EXTRA_PLACENAME)
        val urlImage = intent.getStringExtra(EXTRA_URL_IMAGE)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)
        val category = intent.getStringExtra(EXTRA_CATEGORY)
        val rating = intent.getDoubleExtra(EXTRA_RATING, 0.0)
        val score = intent.getIntExtra(EXTRA_SCORE, 0)
        val latitude = intent.getDoubleExtra(EXTRA_LATITUDE, 0.0)
        val longitude = intent.getDoubleExtra(EXTRA_LONGITUDE, 0.0)
        val city = intent.getStringExtra(EXTRA_CITY)
        setContent {
            JournieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PlaceScreen(
                        title = placeName.toString(),
                        urlImage = urlImage.toString(),
                        description = description.toString(),
                        category = category.toString(),
                        rating = rating,
                        score = score,
                        latitude = latitude,
                        longitude = longitude,
                        city = city.toString()
                    )
                }
            }
        }
    }

    companion object {
        val EXTRA_PLACENAME = "extra_placename"
        val EXTRA_URL_IMAGE = "extra_url_image"
        val EXTRA_DESCRIPTION = "extra_description"
        val EXTRA_CATEGORY = "extra_category"
        val EXTRA_RATING = "extra_rating"
        val EXTRA_SCORE = "extra_score"
        val EXTRA_LATITUDE = "extra_latitude"
        val EXTRA_LONGITUDE = "extra_longitude"
        val EXTRA_CITY = "extra_city"
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    JournieTheme {

    }
}