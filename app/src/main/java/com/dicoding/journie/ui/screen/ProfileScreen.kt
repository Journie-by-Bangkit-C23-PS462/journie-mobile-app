package com.dicoding.journie.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.journie.ui.theme.JournieTheme
import com.dicoding.journie.R


@Composable
fun ProfileScreen(
    username: String,
    email: String,
    age: Int,
    isMale: Boolean
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
                .background(Color.Black)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 25.dp, end = 25.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = username,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = email,
                    color = Color.White,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Badge(
                        modifier = Modifier,
                        backgroundColor = Color.Yellow,
                        contentColor = Color.Black,
                    ) {
                        Text(text = "Umur: ${age}")
                    }
                    Badge(
                        modifier = Modifier,
                        backgroundColor = Color.Yellow,
                        contentColor = Color.Black,
                    ) {
                        Text(
                            text = if (isMale) "Laki-Laki" else "Perempuan"
                        )
                    }
                }
            }
        }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 25.dp, end = 25.dp, top = 15.dp)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                backgroundColor = Color.White,
                elevation = 1.dp,
                shape = MaterialTheme.shapes.medium
            ) {
                Row(modifier = Modifier.fillMaxSize().padding(start = 12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.baseline_language_24), contentDescription = "icon change language" )
                    Text(text = "Ganti Bahasa")
                    Badge(
                        modifier = Modifier,
                        backgroundColor = Color.Gray,
                        contentColor = Color.White,
                    ) {
                        Text(text = "Coming Soon")
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                backgroundColor = Color.White,
                elevation = 1.dp,
                shape = MaterialTheme.shapes.medium
            ) {
                Row(modifier = Modifier.fillMaxSize().padding(start = 12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.baseline_logout_24), contentDescription = "icon to log out" )
                    Text(text = "Logout")
                    Badge(
                        modifier = Modifier,
                        backgroundColor = Color.Gray,
                        contentColor = Color.White,
                    ) {
                        Text(text = "Coming Soon")
                    }
                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPrevoew() {
    JournieTheme {
        ProfileScreen(
            username = "Rafa Ahamada Wijaya",
            email = "arke.fallen@gmail.com",
            age = 21,
            true
        )
    }
}