package com.dicoding.journie.ui.components.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dicoding.journie.ui.theme.JournieTheme

@Composable
fun RecommendationCard(
    modifier: Modifier = Modifier.padding(top = 12.dp, bottom = 12.dp, start = 25.dp, end = 25.dp),
    name : String,
    subname : String,
    duration: Int,
    urlImage: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(5.dp),
        backgroundColor = Color.White,
        elevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Row(
                modifier = Modifier.fillMaxHeight(),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = urlImage,
                    contentDescription = "recommendation image",
                    modifier = Modifier.clip(RoundedCornerShape(5.dp)
                ))
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text(
                            text = name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = subname,
                            fontSize = 10.sp,
                            color = Color.Gray,
                        )
                    }
                    Column {
                        Text(
                            text = "Durasi",
                            fontSize = 10.sp,
                        )
                        Text(
                            text = "${duration} Menit",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Lihat Detail", fontWeight = FontWeight.SemiBold, color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationCardPreview(){
    JournieTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)) {
            RecommendationCard(
                name = "Bandung",
                subname = "Jawa Barat",
                duration = 250,
                urlImage = "https://th.bing.com/th/id/R.8e0fb11dc04dd053d6cdbcd52dc9bcc6?rik=iD%2foSogxC9oC2g&riu=http%3a%2f%2f2.bp.blogspot.com%2f-DLWPx3Ldj_A%2fVcTOMJ6iwjI%2fAAAAAAAABUY%2fh9c_lfakEos%2fs1600%2fsate.jpg&ehk=5ImaTipkL%2fcjJK%2fTtXOQ4SIHtRIGRk8hU89wPbchHqQ%3d&risl=&pid=ImgRaw&r=0"
            )
        }
    }
}