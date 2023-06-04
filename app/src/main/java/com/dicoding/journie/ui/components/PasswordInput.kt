package com.dicoding.journie.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.journie.R
import com.dicoding.journie.ui.theme.JournieTheme

@Composable
fun PasswordInput(
    modifier: Modifier = Modifier
) {
    var value by remember { mutableStateOf("") }
    OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            value = newText
        },
        placeholder = {
            Text(stringResource(R.string.password_hint))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordInputPreview() {
    JournieTheme {
        PasswordInput()
    }
}