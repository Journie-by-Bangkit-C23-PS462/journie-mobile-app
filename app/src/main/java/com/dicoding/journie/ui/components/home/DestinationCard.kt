package com.dicoding.journie.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dicoding.journie.R
import com.dicoding.journie.ui.theme.JournieTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DestinationCard(
    name: String,
    subname: String,
    description: String,
    urlImage: String,
    modifier: Modifier
) {
    Card(
        shape = RoundedCornerShape(10),
        elevation = 1.dp,
        modifier = modifier.size(width = 220.dp, height = 150.dp),
        onClick = {

        }
    ) {
        AsyncImage(
            model = urlImage,
            contentDescription = "destination image",
            contentScale = ContentScale.Crop
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black
                    ),
                    startY = 200f
                )
            )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(text = name, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                Text(text = subname, color = Color.Yellow, fontSize = 12.sp)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DestinationCardPreview() {
    JournieTheme {
        DestinationCard(
            name = "Jakarta",
            subname = "DKI Jakarta",
            description = "Ini ibu kota",
            urlImage = "https://th.bing.com/th/id/R.e70e26ad920880ca0e7d9afceff7cee6?rik=U2WVPDmd%2bOM2Sg&riu=http%3a%2f%2fblog.reservasi.com%2fwp-content%2fuploads%2f2015%2f08%2fTidak-Liburan-Ke-Luar-Kota-Ide-Staycation-Dan-Liburan-Di-Jakarta-Ini-Akan-Sangat-Membantu-Kalian.jpg&ehk=eB%2bSd1G7jxyo1pXhvaVb1VoB%2blKyktQ5rrc53mlXMVU%3d&risl=&pid=ImgRaw&r=0",
            modifier = Modifier
        )
    }
}