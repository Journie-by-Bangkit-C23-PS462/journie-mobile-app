package com.dicoding.journie.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.journie.ui.theme.JournieTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier
) {
    RegisterContent()
}

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .weight(1f)
                .padding(top = 80.dp)
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    JournieTheme {
        RegisterContent()
    }
}