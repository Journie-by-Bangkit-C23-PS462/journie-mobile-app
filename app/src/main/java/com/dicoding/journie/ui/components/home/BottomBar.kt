package com.dicoding.journie.ui.components.home

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.journie.data.local.BottomBarItem
import com.dicoding.journie.ui.theme.JournieTheme

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    BottomAppBar(
        modifier = modifier.shadow(elevation = 5.dp),
        backgroundColor = Color.White,
        elevation = 50.dp
    ) {
        val navigationItems = listOf(
            BottomBarItem(
                title = "Home",
                icon = Icons.Filled.Home
            ),
            BottomBarItem(
                title = "Explore",
                icon = Icons.Filled.Place
            ),
            BottomBarItem(
                title = "Profile",
                icon = Icons.Filled.Person
            )
        )
        navigationItems.map {
            BottomNavigationItem(
                selected = it.title == navigationItems[0].title,
                onClick = {},
                icon = {
                       Icon(imageVector = it.icon, contentDescription = "icon")
                },
                label = {
                    Text(text = it.title)
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    JournieTheme {
        BottomBar()
    }
}