package com.dicoding.journie.ui.components.place

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.dicoding.journie.ui.theme.JournieTheme


@Composable
fun VisitMapButton(
    modifier: Modifier = Modifier,
    latitude: Double,
    longitude: Double,
    placeName: String
) {
    val context = LocalContext.current
    OutlinedButton(
        onClick = {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=${latitude},${longitude} (${placeName})")
            )
            context.startActivity(intent)
        },
        border = BorderStroke(2.dp, Color.Black),
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        Text(
            text = "Lihat di Google Maps",
            modifier = Modifier.align(Alignment.CenterVertically),
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VisitMapButtonPreview() {
    JournieTheme {
        Box(modifier = Modifier.padding(15.dp)) {
            VisitMapButton(
                latitude = -7.2420758,
                longitude = 112.7368158,
                placeName = "Gereja Perawan Maria Tak Berdosa Surabaya"
            )
        }
    }
}